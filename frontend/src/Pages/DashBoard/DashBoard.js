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



/**
 * DashBoard component which represents the landing-page after
 * a successful user authentication. It displays a short summary
 * from a given date regarding the status of the budget as well
 * as what transactions which was made at the given date.
 * 
 * The data is displayed through regular text and through the
 * BarChart component which shows the sum of incomes vs. the
 * sum of expenses. The date which are to be summarised can be
 * picked using the DatePicker component on the top of the page.
 */
export function DashBoard() {

    /** String representation of the given date. */
    const [strDate, setStrDate] = useState('2023-02-10');


    /* Dayjs Date representation of the given date. */
    const [date, setDate] = useState(dayjs(strDate));


    /* Sum of expenses at the given date. */
    const [expenses, setExpenses] = useState(0);

    /* Sum of incomes at the given date. */
    const [incomes, setIncomes] = useState(0);

    /* Budget boundary which represents the value which the user wants hold in the budget. */
    const [balance, setBalance] = useState(0);

    /* Sum of incomes up to the given date to be displayed in the BarChart component. */
    const [sumIncome, setSumIncome] = useState(0);

    /* Sum of expenses up to the given date to be displayed in the BarChart component. */
    const [sumExpense, setSumExpense] = useState(0);



    /**
     * Gets all transactions labeled as an expense at the given date
     * and summarises them. The expenses variable will be set to
     * the sum of theese transaction values.
     */
    const getExpenses = () => { 

        /** Object containing the expenses at the given date */
        const obj = getExpensesAsDateObject();
        let sumExpenses = 0;
    
        /* Summing all expenses. */
        for (let i = 0; i < obj.dates.length; i++) {
            if (obj.dates[i] == strDate) {
                sumExpenses += obj.expenses[i];
            }
        }

        /* Updates the expenses variable. */
        setExpenses(sumExpenses);
    };



    /**
     * Gets all transactions labeled as an income at the given date
     * and summarises them. The incomes variable will be set to
     * the sum of theese transaction values.
     */
    const getIncomes = () => { 

        /** Object containing the incomes at the given date */
        const obj = getIncomesAsDateObject();
        let sumIncomes = 0;

        /* Summing all incomes. */
        for (let i = 0; i < obj.dates.length; i++) {
            if (obj.dates[i] == strDate) {
                sumIncomes += obj.incomes[i];
            }
        }

        /* Updates the incomes variable. */
        setIncomes(sumIncomes);
    };



    /**
     * Fetches all incomes, expenses and a list of transaction dates
     * as lists and summarises the income and expenses of the whole
     * span of the budget. The sums are used to update the sumIncome
     * and sumExpense variables, which again is used to display the
     * total income and spendage of the budget up to the given date.
     */
    const getSumOfTransactionsAndBalance = async () => {

        /** List of all income transactions. */
        const incomeList = await getIncomesAsDateList();

        /** List of all expense transactions. */
        const expenseList = await getExpensesAsDateList();

        /** List of all dates which transactions has been made. */
        const dateList = await getTransactionDatesList();

        /** Index of the given date in the datelist. */
        const index = dateList.indexOf(strDate);

        let sumIncome = 0;
        let sumExpense = 0;

        /* Summing all incomes and expenses up to the given date. */
        for (let i = 0; i <= index; i++) {
            sumIncome += incomeList[i];
            sumExpense += expenseList[i];
        }

        /* Updates the sumIncome and sumExpense variables. */
        setSumIncome(sumIncome);
        setSumExpense(sumExpense);
    };



    /**
     * UseEffect hook which runs on every rendering. It checks
     * if the user is authenticated before updating the data to
     * be displayed on the budget page.
     */
    useEffect( () => {
        if (SESSION.getAuth()) {
            getExpenses();
            getIncomes();
            getSumOfTransactionsAndBalance();
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