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

function EditTransaction(props) {
    const [open, setOpen] = useState(false);
    const [transaction, setTransaction] = useState({
        tname: '',
        value: '',
        description: '',
        date: '',
    });


    const handleClickOpen = () => {
        setTransaction({
            tname: props.data.row.tname,
            value: props.data.row.value,
            description: props.data.row.description,
            date: props.data.row.date,
        });
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleChange = (event) => {
        setTransaction({
            ...transaction,
            [event.target.name]: event.target.value
        });
    };

    const handleSave = () => {
        props.updateTransaction(transaction, props.data.id, props.type);
        handleClose();
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
                                value={dayjs(transaction.date)}
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