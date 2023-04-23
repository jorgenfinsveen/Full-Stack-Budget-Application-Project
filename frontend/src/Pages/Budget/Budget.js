import './Budget.css';
import dayjs from 'dayjs';
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';
import EditTransaction from './Transaction/EditTransaction';
import AddTransaction from './Transaction/AddTransaction';
import Button from '@mui/material/Button';
import React, { useEffect, useState } from 'react';
import { Slider } from '../../Components/Slider';
import { DemoContainer, DemoItem } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateRangePicker } from '@mui/x-date-pickers-pro/DateRangePicker';
import { HttpInterface } from '../../Session/HttpInterface';
import { BUDGET, SESSION, EXPENSES, INCOMES } from '../../Session/Session';
import { DataGrid, GridToolbarContainer, GridToolbarExport, gridClasses } from '@mui/x-data-grid';



/**
 * Component which are to be displayed on the budget-page. It contains
 * a Slider component for adjusting the boundary of the budget as well
 * as DatePicker components for adjusting the starting date and ending
 * date of the budget. It also displays DataGrid tables which contains incomes
 * and transactions with the possibility of adding, editing, and deleting
 * a transaction. Lastly, it displays a short summary of the status of the
 * budget as clean text.
 */
export function Budget() {

    /**
     * Row cell which contains a button for editing a transaction
     * labeled as an income.
     */
    const editIncomeCell = {
        field: '_links.transaction.href',
        headerName: '',
        sortable: false,
        filterable: false,
        width: 10,
        renderCell: row =>
            <EditTransaction 
                data = {row} 
                updateTransaction = {updateTransaction}
                type = {'Income'}
            />
    };


    /**
     * Row cell which contains a button for editing a transaction
     * labeled as an expense.
     */
    const editExpenseCell = {
        field: '_links.transaction.href',
        headerName: '',
        sortable: false,
        filterable: false,
        width: 10,
        renderCell: row =>
            <EditTransaction 
                data = {row} 
                updateTransaction = {updateTransaction}
                type = {'Expense'}
            />
    };


    /**
     * Row cell which contains a button for deleting a transaction.
     */
    const deleteCell = {
        field: '_links.self.href',
        headerName: '',
        sortable: false,
        filterable: false,
        width: 10,
        renderCell: row =>
            <IconButton onClick = {() => onDelClick(row.id)}>
                <DeleteIcon color = "error" />
            </IconButton>
    };



    /**
     * Row cells which contains the name, date, and value of an income
     * as well as the edit button and the delete button.
     */
    const incomeColumns = [
        {field: 'tname', headerName: 'Title', width: 122},
        {field: 'date', headerName: 'Date', width: 98},
        {field: 'value', headerName: 'Price', width: 72},
        editIncomeCell,
        deleteCell
    ];



    /**
     * Row cells which contains the name, date, and value of an expense
     * as well as the edit button and the delete button.
     */
    const expenseColumns = [
        {field: 'tname', headerName: 'Title', width: 122},
        {field: 'date', headerName: 'Date', width: 98},
        {field: 'value', headerName: 'Price', width: 72},
        editExpenseCell,
        deleteCell
    ];



    /** Array of all transactions categorized as expenses. */
    const [expenses, setExpenses] = useState([]);

    /** Array of all transactions categorized as incomes. */
    const [incomes,  setIncomes]  = useState([]);

    /** Sum of all income transactions. */
    const [totalIncome, setTotalIncome] = useState();

    /** Sum of all expense transactions. */
    const [totalExpense, setTotalExpense] = useState();

    /** Start- and end-date of the budget. */
    const [budgetDates, setBudgetDates] = useState([
        dayjs('2023-03-22'), 
        dayjs('2023-05-16')
    ]);



    /**
     * Fetches all expenses associated with the budget ordered by
     * the date of the transactions. Since each expense is stored
     * in the database with a negative value, each transaction is
     * iterated through in order to convert the value into a positive
     * integer instead.
     */
    const getExpenses = async () => {

        /** Retrieves the array of expenses from the HTTP Interface. */
        const exp = await HttpInterface.fetchAllExpenses2();
        if (exp !== undefined) {

            /* Iterates through each element in the array. */
            for (let trans of exp) {

                /* Iterates through each field of the given transaction object. */
                for (let field in trans) {

                    /* If the field is a number, the value is multiplied with -1. */
                    if (!isNaN(trans[field])) {
                        trans[field] = trans[field] * -1;
                        break;
                    }
                }
            }

            /* Updates the expenses variable. */
            setExpenses(exp); 
        }
    };



    /**
     * Fetches all incomes associated with the budget ordered by
     * the date of the transactions.
     */
    const getIncomes = async () => {

        /** Retrieves the array of incomes from the HTTP Interface. */
        const inc = await HttpInterface.fetchAllIncomes2();

        /* Updates the incomes variable. */
        setIncomes(inc); 
    };



    /**
     * Updates a given transaction with new data. The new transaction
     * is forwarded to the HTTP Interface, which again sends a /PUT 
     * request to the server.
     * 
     * @param trans The new transaction.
     * @param link  The link to the transaction.
     * @param type  The type of the transaction which is either "Income" or "Expense".
     */
    const updateTransaction = (trans, link, type) => {

        /* If the transaction is an expense, the value is turned to a negative number. */
        if (type === 'Expense') {
            trans.value = trans.value * (-1);
        }

        /* Splits the provided link at each '/'. */
        let splitted = link.split('/');

        /* Retrieves the ID of the transaction which is after the last '/'. */
        let transactionId = Number(splitted[splitted.length - 1]);

        /** The updated transaction on a format which can be sent to the server. */
        const newTransaction = {
            name: trans.tname,
            value: Number(trans.value),
            description: trans.description,
            date: trans.date,
            bid: Number(BUDGET.budgetId)
        };

        /* HTTP Interface makes a /PUT request to the server with the ID and the new transaction. */
        HttpInterface.updateTransaction(transactionId, newTransaction);

        /* Fetches the expenses and incomes again. */
        getExpenses();
        getIncomes();
    };



    /**
     * Adds a new transaction to the budget. The transaction is forwarded to the
     * HTTP Interface which again sends a /POST request to the server.
     */
    const addTransaction = (trans, type) => {

        /* If the transaction is an expense, the value is turned to a negative number. */
        if (type === 'Expense') {
            trans.value = trans.value * (-1);
        }

        /** The new transaction on a format which can be sent to the server. */
        const newTransaction = {
            name: trans.tname,
            value: Number(trans.value),
            description: trans.description,
            date: trans.date,
            bid: Number(BUDGET.budgetId)
        };

        /* HTTP Interface makes a /POST request to the server with the new transaction. */
        HttpInterface.addTransaction(newTransaction);

        /* Fetches the expenses and incomes again. */
        getExpenses();
        getIncomes();
    };


    /**
     * Deletes a given transaction by having the HTTP Interface send a /DELETE
     * request to the server.
     * 
     * @param link The link to the transaction to be deleted.
     */
    const onDelClick = (link) => {

        /* Prompts the user to confirm deletion. */
        const confirmed = window.confirm("Delete this transaction?");

        if (confirmed) {

            /* Retrieves the ID of the transaction from the link. */
            let splitted = link.split('/');
            let transactionId = Number(splitted[splitted.length - 1]);

            /* HTTP Interface makes a /DELETE request to the server of the given transaction. */
            HttpInterface.deleteTransaction(transactionId);
        }
    };



    /**
     * Updates the budget upon pressing the apply changes button by updating
     * the start- and end-date and having the HTTP Interface send a /PUT request
     * to the server.
     */
    const handleApply = () => {
        BUDGET.setStartDate(budgetDates[0].format('YYYY-MM-DD'));
        BUDGET.setEndDate(budgetDates[1].format('YYYY-MM-DD'));
        HttpInterface.updateBudget();
    };



    /**
     * Updates the values which are to be displayed in the summary of
     * the budget status.
     */
    const updateStatus = () => {
        let sumExp = 0;
        let sumInc = 0;

        /* Sums all expense values. */
        for (let e of EXPENSES) {
            sumExp += e.value;
        }

        /* Sums all income values. */
        for (let i of INCOMES) {
            sumInc += i.value;
        }

        /* Updates the totalExpense and totalIncome variables. */
        setTotalExpense(sumExp * (-1));
        setTotalIncome(sumInc);
    };



    /**
     * Updates the start- and ending-date of the budget.
     */
    const updateBudgetDates = () => {
        let start = BUDGET.startDate;
        let end = BUDGET.endDate;
        setBudgetDates([dayjs(start), dayjs(end)]);
    };



    /**
     * UseEffect hook which activates upon changes
     * to the incomes or expenses variables. Upon
     * activation, it refreshes all data which are to
     * be displayed on the budget-page.
     */
    useEffect( () => {
        let interval = setInterval(() => {
            if (SESSION.getAuth()) {
                getExpenses();
                getIncomes();
                updateStatus();
                updateBudgetDates();
                clearInterval(interval);
            }
        }, 10);
    }, [incomes, expenses]);



    return( 
        <article>
            <header>
                <h1>Budget</h1>
            </header>
            <hr/>
            <div id="expense-container">
                <h2 id="expense-container-label">Expense</h2>
                <div style={{ minHeight: '100%', maxHeight: '100%', width: '100%' }}>
                    <DataGrid
                        rows = {expenses}
                        autoHeight = {true}
                        getRowHeight={({ id, densityFactor }) => {
                            if (expenses.length %2 !== 0 && id == expenses[expenses.length - 1]._links.self.href) {
                              return 104 * densityFactor;
                            }
                            return null;
                          }
                        }
                        columns = {expenseColumns}
                        disableRowSelectionOnClick = {true}
                        getRowId = {row => row._links.self.href}
                        components = {{Toolbar: CustomToolbar}}
                        initialState={{
                            pagination: { paginationModel: { pageSize: 2 } },
                        }}
                        pageSizeOptions={[2]}
                    />
                </div>
                <br/>
                <AddTransaction addTransaction = { addTransaction } type = {'Expense'}/>
            </div>
            <div id="income-container">
                <h2 id="income-container-label">Income</h2>
                <div style={{  minHeight: '100%', maxHeight: '100%', width: '100%' }}>
                    <DataGrid
                        rows = {incomes}
                        autoHeight = {true}
                        getRowHeight={({ id, densityFactor }) => {
                            if (incomes.length %2 !== 0 && id == incomes[incomes.length - 1]._links.self.href ) {
                              return 104 * densityFactor;
                            }
                            return null;
                          }
                        }
                        columns = {incomeColumns}
                        disableRowSelectionOnClick = {true}
                        getRowId = {row => row._links.self.href}
                        components = {{Toolbar: CustomToolbar}}
                        initialState={{
                            pagination: { paginationModel: { pageSize: 2 } },
                        }}
                        pageSizeOptions={[2]}
                    />
                </div>
                <br/>
                <AddTransaction addTransaction = { addTransaction } type = {'Income'} />
            </div>
            <div id="budget-margin">
                <h2 id="margin-label">Margin</h2>
                <Slider/>
                <br/><br/>
                <Button 
                    variant = "contained"
                    onClick = {handleApply}
                >
                    Apply Changes
                </Button>
                <br/>
                <br/>
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DemoContainer components={['DateRangePicker', 'DateRangePicker']}>
                        <DemoItem label="" component="DateRangePicker">
                            <DateRangePicker
                                value={budgetDates}
                                onChange={(budgetDates) => setBudgetDates(budgetDates)}
                            />
                        </DemoItem>      
                    </DemoContainer>
                </LocalizationProvider>
            </div>
            <div id="status-container">
                <h2 id="status-container-label">Staus</h2>
                <p>Total income: <i id="total-income"> KR {totalIncome}</i></p>
                <p>Total expense: <i id="total-expense"> KR {totalExpense}</i></p>
                <p>Budget spendings: <i id="budget-spendings"> KR {totalExpense - totalIncome} / {BUDGET.getBoundary()}</i></p>
                <p>Margin: <i id="margin"> KR {BUDGET.getBoundary() + totalIncome - totalExpense}</i></p>
            </div>
        </article>
    );
}

function CustomToolbar() {
    return (
        <GridToolbarContainer
            className = {gridClasses.toolbarContainer}>
            <GridToolbarExport />
        </GridToolbarContainer>
    );
};