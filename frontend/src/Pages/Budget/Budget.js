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



export function Budget() {

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

    const incomeColumns = [
        {field: 'tname', headerName: 'Title', width: 122},
        {field: 'date', headerName: 'Date', width: 98},
        {field: 'value', headerName: 'Price', width: 72},
        editIncomeCell,
        deleteCell
    ];

    const expenseColumns = [
        {field: 'tname', headerName: 'Title', width: 122},
        {field: 'date', headerName: 'Date', width: 98},
        {field: 'value', headerName: 'Price', width: 72},
        editExpenseCell,
        deleteCell
    ];

    const [expenses, setExpenses] = useState([]);
    const [incomes,  setIncomes]  = useState([]);
    const [totalIncome, setTotalIncome] = useState();
    const [totalExpense, setTotalExpense] = useState();
    const [budgetDates, setBudgetDates] = useState([
        dayjs('2023-03-22'), 
        dayjs('2023-05-16')
    ]);

    const getExpenses = async () => {
        const exp = await HttpInterface.fetchAllExpenses2();
        if (exp !== undefined) {
            for (let trans of exp) {
                for (let field in trans) {
                    if (!isNaN(trans[field])) {
                        trans[field] = trans[field] * -1;
                        break;
                    }
                }
            }
            setExpenses(exp); 
        }
    };

    const getIncomes = async () => {
        const inc = await HttpInterface.fetchAllIncomes2();
        setIncomes(inc); 
    };

    const updateTransaction = (trans, link, type) => {
        if (type === 'Expense') {
            trans.value = trans.value * (-1);
        }
        let splitted = link.split('/');
        let transactionId = Number(splitted[splitted.length - 1]);
        
        const newTransaction = {
            name: trans.tname,
            value: Number(trans.value),
            description: trans.description,
            date: trans.date,
            bid: Number(BUDGET.budgetId)
        };
        HttpInterface.updateTransaction(transactionId, newTransaction);

        getExpenses();
        getIncomes();
    };

    const addTransaction = (trans, type) => {
        if (type === 'Expense') {
            trans.value = trans.value * (-1);
        }
        const newTransaction = {
            name: trans.tname,
            value: Number(trans.value),
            description: trans.description,
            date: trans.date,
            bid: Number(BUDGET.budgetId)
        };
        HttpInterface.addTransaction(newTransaction);

        getExpenses();
        getIncomes();
    };

    const onDelClick = (link) => {
        const confirmed = window.confirm("Delete this transaction?");

        if (confirmed) {
            let splitted = link.split('/');
            let transactionId = Number(splitted[splitted.length - 1]);
            HttpInterface.deleteTransaction(transactionId);
        }
    };

    const handleApply = () => {
        BUDGET.setStartDate(budgetDates[0].format('YYYY-MM-DD'));
        BUDGET.setEndDate(budgetDates[1].format('YYYY-MM-DD'));
        HttpInterface.updateBudget();
    };

    const updateStatus = () => {
        let sumExp = 0;
        let sumInc = 0;

        for (let e of EXPENSES) {
            sumExp += e.value;
        }
        for (let i of INCOMES) {
            sumInc += i.value;
        }

        setTotalExpense(sumExp * (-1));
        setTotalIncome(sumInc);
    };

    const updateBudgetDates = () => {
        let start = BUDGET.startDate;
        let end = BUDGET.endDate;
        setBudgetDates([dayjs(start), dayjs(end)]);
    };

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