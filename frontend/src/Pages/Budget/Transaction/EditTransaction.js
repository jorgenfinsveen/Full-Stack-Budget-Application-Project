import React, { useState} from 'react';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import EditIcon from '@mui/icons-material/Edit';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import dayjs from 'dayjs';



/**
 * Component for editing an existing transaction. It consists of a pop-up
 * window with input fields which represents the data associated with
 * the transaction.
 * 
 * @param props.data The transaction data which are to be displayed in the editor.
 * @param props.type {String} The type of the transaction which is either "Income" or "Expense".
 * @param props.updateTransaction Function for updating a given transaction.
 */
function EditTransaction(props) {

    /** Boolean indicating whether to display the pop-up window or not. */
    const [open, setOpen] = useState(false);

    /** The new transaction with its given data. */
    const [transaction, setTransaction] = useState({
        tname: '',
        value: '',
        description: '',
        date: '',
    });



    /**
     * Activates upon pressing the button for editing a transaction. It
     * displays the pop-up window and inserts the current values of the 
     * transaction in the input fields.
     */
    const handleClickOpen = () => {
        setTransaction({
            tname: props.data.row.tname,
            value: props.data.row.value,
            description: props.data.row.description,
            date: props.data.row.date,
        });
        setOpen(true);
    };



    /**
     * Activates upon pressing the cancel button. It will close
     * the pop-up window upon activation.
     */
    const handleClose = () => {
        setOpen(false);
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
     * Activates upon pressing the add button. It calls
     * on the updateTransaction function provided by the props
     * parameter of the EditTransaction component with the
     * updated transaction, the transaction id and the type 
     * of transaction as parameters. After, it will close the 
     * pop-up window.
     */
    const handleSave = () => {
        props.updateTransaction(transaction, props.data.id, props.type);
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
            <IconButton onClick = {handleClickOpen}>
                <EditIcon color = "primary" />
            </IconButton>
            <Dialog open = {open} onClose = {handleClose}>
                <DialogTitle>Edit Transaction</DialogTitle>
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
                                defaultValue={dayjs(transaction.date)}
                                onChange={(value) => handleDateChange(value)}
                            />
                        </LocalizationProvider>
                    </Stack>
                </DialogContent>
                <DialogActions>
                    <Button onClick = {handleClose}>Cancel</Button>
                    <Button onClick = {handleSave}>Save</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default EditTransaction;