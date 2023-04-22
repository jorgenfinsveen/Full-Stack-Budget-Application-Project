import './DashBoard.css';
import dayjs from 'dayjs';
import { BarChart } from "../../Components/BarChart";
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { useState, useEffect } from "react";
import { 
    SESSION,
    BUDGET, 
    getExpensesAsDateObject, 
    getIncomesAsDateObject,
    getExpensesAsDateList, 
    getIncomesAsDateList,
    getTransactionDatesList 
} from '../../Session/Session';



export function DashBoard() {

    const [strDate, setStrDate] = useState('2023-04-18');
    const [date, setDate] = useState(dayjs(strDate));
    const [expenses, setExpenses] = useState(0);
    const [incomes, setIncomes] = useState(0);
    const [balance, setBalance] = useState(0);
    const [sumIncome, setSumIncome] = useState(0);
    const [sumExpense, setSumExpense] = useState(0);


    const getExpenses = () => { 
        const obj = getExpensesAsDateObject();
        let sumExpenses = 0;
    
        for (let i = 0; i < obj.dates.length; i++) {
            if (obj.dates[i] == strDate) {
                sumExpenses += obj.expenses[i];
            }
        }
        setExpenses(sumExpenses);
    };
      
    const getIncomes = () => { 
        const obj = getIncomesAsDateObject();
        let sumIncomes = 0;

        for (let i = 0; i < obj.dates.length; i++) {
            if (obj.dates[i] == strDate) {
                sumIncomes += obj.incomes[i];
            }
        }
        setIncomes(sumIncomes);
    };


    const getSumIncomeAndBalance = async () => {
        const incomeList = await getIncomesAsDateList();
        const expenseList = await getExpensesAsDateList();
        const dateList = await getTransactionDatesList();
        const index = dateList.indexOf(strDate);

        let sumIncome = 0;
        let sumExpense = 0;

        for (let i = 0; i <= index; i++) {
            sumIncome += incomeList[i];
            sumExpense += expenseList[i];
        }

        setSumIncome(sumIncome);
        setSumExpense(sumExpense);
    };


    useEffect( () => {
        if (SESSION.getAuth()) {
            getExpenses();
            getIncomes();
            getSumIncomeAndBalance();
            setBalance(BUDGET.boundary);
        }
    });
    

    return( 
        <article>
            <header>
                <div id="title">
                    <h1>Dashboard</h1>
                </div>
                <div id="date">
                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                        <DatePicker 
                            label="" 
                            value={date}
                            onChange={(date) => 
                                {
                                    setStrDate(date.format('YYYY-MM-DD'));
                                    setDate(date);
                                }
                            }
                        />
                    </LocalizationProvider>
                </div>
            </header>
            <hr/>
            <div id="budget-balance">
                <h2>Total available balance</h2>
                <p id="budget-balance-placeholder">Kr {balance}</p>
            </div>
            <hr/>
            <div id="budget-summary">
                <div id="budget-summary-income">
                    <h3>Income</h3>
                    <p id="budget-income-placeholder">Kr {incomes}</p>
                </div>
                <div id="budget-summary-expense">
                    <h3>Expense</h3>
                    <p id="budget-expense-placeholder">Kr {expenses}</p>
                </div>
                <div id="budget-summary-remaining">
                    <h3>Budget Remaining</h3>
                    <p id="budget-remeinder-placeholder">Kr {balance + sumIncome - sumExpense}</p>
                </div>
            </div>
            <hr/>
            <h2 id="bar-title">Income & Expense</h2>
            <div id="barchart">
                <BarChart 
                    income={incomes}
                    expense = {expenses}
                />
            </div>
        </article>
    );
}