import React, { useState} from 'react';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import dayjs from 'dayjs';

function AddIncome(props) {

    const [todayICO, setTodayICO] = useState('2023-04-21');
    const [open, setOpen] = useState(false);
    const [transaction, setTransaction] = useState({
        tname: '',
        value: '',
        description: '',
        date: todayICO,
    });

    const handleClickOpen = () => {
        const today = new Date();
        const ym = (today.getMonth()+1 < 10) ? '-0' : '-';
        const md = (today.getDate() < 10) ? '-0' : '-';
        setTodayICO(today.getFullYear() + ym + (today.getMonth()+1) + md + today.getDate());
        setOpen(true);
    };

    const handleChange = (event) => {
        setTransaction({
            ...transaction,
            [event.target.name]: event.target.value
        });
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleSave = () => {
        props.addTransaction(transaction, props.type);
        handleClose();
    };

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

export default AddIncome;