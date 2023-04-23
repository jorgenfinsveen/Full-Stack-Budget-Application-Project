import React, { useState} from 'react';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import dayjs from 'dayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';



/**
 * Component for creating a new transaction. It consists of a pop-up
 * window with input fields which represents the data associated with
 * the new transaction.
 * 
 * @param props.type {String} The type of the transaction which is either "Income" or "Expense".
 * @param props.addTransaction Function for adding the new transaction.
 */
function AddTransaction(props) {

    /** Today's date as a string on the YYYY-MM-DD format. */
    const [todayICO, setTodayICO] = useState('2023-04-21');

    /** Boolean indicating whether to display the pop-up window or not. */
    const [open, setOpen] = useState(false);

    /** The new transaction with its given data. */
    const [transaction, setTransaction] = useState({
        tname: '',
        value: '',
        description: '',
        date: todayICO,
    });



    /**
     * Activates upon pressing the button for adding a new transaction. It
     * displays the pop-up window and retrives the current date as a default
     * value in the DatePicker component.
     */
    const handleClickOpen = () => {

        /* Retrieves the current date and adjusts it to the YYYY-MM-DD format. */
        const today = new Date();
        const ym = (today.getMonth()+1 < 10) ? '-0' : '-';
        const md = (today.getDate() < 10) ? '-0' : '-';

        /* Updates the todayICO variable with the date. */
        setTodayICO(today.getFullYear() + ym + (today.getMonth()+1) + md + today.getDate());

        /* Sets the setOpen variable to true. */
        setOpen(true);
    };



    /**
     * Activates upon changes in any of the input-fields except the DatePicker.
     * Updates the transaction variable with the values read from the input-
     * fields.
     * 
     * @param event The event which triggers the function.
     */
    const handleChange = (event) => {
        setTransaction({
            ...transaction,
            [event.target.name]: event.target.value
        });
    };



    /**
     * Activates upon pressing the cancel button. It will close
     * the pop-up window upon activation.
     */
    const handleClose = () => {
        setOpen(false);
    };



    /**
     * Activates upon pressing the add button. It calls
     * on the addTransaction function provided by the props
     * parameter of the AddTransaction component with the
     * new transaction and the type of transaction as parameters.
     * After, it will close the pop-up window.
     */
    const handleSave = () => {
        props.addTransaction(transaction, props.type);
        handleClose();
    };



    /**
     * Activates upon changes in the DatePicker component.
     * Updates the transaction date variable with the value
     * read from the DatePicker input on the YYYY-MM-DD format.
     * 
     * @param value The date which are picked in the DatePicker component.
     */
    const handleDateChange = (value) => {
        value = value.format('YYYY-MM-DD');
        setTransaction({
            ...transaction,
            ['date']: value
        });
    };



    
    return (
        <div>
            <Button 
                variant = "contained"
                onClick = {handleClickOpen}>
                Add {props.type}
            </Button>
            <Dialog open = {open} onClose = {handleClose}>
                <DialogTitle>Add {props.type}</DialogTitle>
                <DialogContent>
                    <Stack spacing = {2} mt = {1}>
                        <TextField label = "Name" name = "tname"
                            autoFocus
                            variant = "standard" value = {transaction.tname}
                            onChange = {handleChange}
                        />
                        <TextField label = "Value" name = "value"
                            autoFocus
                            variant = "standard" value = {transaction.value}
                            onChange = {handleChange}
                        />
                        <TextField label = "Description" name = "description"
                            autoFocus
                            variant = "standard" value = {transaction.description}
                            onChange = {handleChange}
                        />
                        <LocalizationProvider dateAdapter={AdapterDayjs}>
                            <DatePicker
                                name = "date"
                                defaultValue={dayjs(todayICO)}
                                onChange={(value) => handleDateChange(value)}
                            />
                        </LocalizationProvider>
                    </Stack>
                </DialogContent>
                <DialogActions>
                    <Button onClick = {handleClose}>Cancel</Button>
                    <Button onClick = {handleSave}>Add</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default AddTransaction;