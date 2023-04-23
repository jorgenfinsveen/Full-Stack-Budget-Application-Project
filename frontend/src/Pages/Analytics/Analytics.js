import './Analytics.css';
import React, { useState, useEffect } from 'react';
import { BarChart } from "../../Components/BarChart";
import { LineChart } from "../../Components/LineChart";
import { 
    SESSION, 
    getExpensesAsDateList, 
    getIncomesAsDateList,
    getTransactionDatesList 
} from '../../Session/Session';



/**
 * Analytics component which are to be displayed in the analytics 
 * page of the application. It provides a linegraph for displaying
 * all incomes and expenses ordered by date using the LineChart
 * component as well as the sum of incomes and expenses in a bar
 * diagram using the BarChart component.
 */
export function Analytics() {

    /** Array of expense-values associated with the budget ordered by date. */
    const [expenses, setExpenses] = useState([]);

    /** Array of income-values associated with the budget ordered by date. */
    const [incomes, setIncomes] = useState([]);

    /** Array of date-labels which transaction associated with the budget has been made. */
    const [labels, setLabels] = useState(['January', 'February', 'March']);

    /** Sum of incomes associated with the budget. */
    const [sumIncomes, setSumIncomes] = useState(0);

    /** Sum of expenses associated with the budget. */
    const [sumExpenses, setSumExpenses] = useState(0);



    /**
     * Retrieves a list of all dates, incomes and expenses associated with
     * the budget and updates the incomeList, expenseList and newLabels
     * variables with these lists.
     */
    const getData = async () => {

        /* Retrieves the transaction and dates as lists. */
        const expenseList = await getExpensesAsDateList();
        const incomeList = await getIncomesAsDateList();
        const newLabels = await getTransactionDatesList();
    
        /* Updates the income, expense and label variables. */
        setIncomes(incomeList);
        setExpenses(expenseList);
        setLabels(newLabels);
    }



    /**
     * Retrieves all incomes and expenses associated with the budget and
     * summarises the values for it to be displayed in the BarChart component.
     */
    const getIncomesAndExpenses = async () => { 

        /* Retrieves the list of incomes and summs the values. */
        const incomeList = await getIncomesAsDateList();
        const sumIncomes = incomeList.reduce((a, b) => {return a + b});
    
        /* Retrieves the list of expenses and summs the values. */
        const expenseList = await getExpensesAsDateList();
        const sumExpenses = expenseList.reduce((a, b) => {return a + b});
    
        /* Updates the sumIncomes and sumExpenses variables. */
        setSumIncomes(sumIncomes);
        setSumExpenses(sumExpenses);
    };



    /**
     * UseEffect hook which is triggered upon user authentication.
     * Upon activation, all data which are to be displayed on the
     * analytics-page is requested.
     */
    useEffect( () => {
        if (SESSION.getAuth()) {
            getData();
            getIncomesAndExpenses();
        }
    }, [SESSION.auth]);



    
    return( 
        <article>
            <header>
                <h1>Analytics</h1>
            </header>
            <hr/>
            <h2>All incomes & expenses</h2>
            <div id="linechart">
                <LineChart
                    labels={labels}
                    incomes={incomes}
                    expenses={expenses}
                />
            </div>
            <hr/>
            <h2>Relationship</h2>
            <div id="barchart">
                <BarChart
                    income={sumIncomes}
                    expense={sumExpenses}
                />
            </div>
        </article>
    );
}