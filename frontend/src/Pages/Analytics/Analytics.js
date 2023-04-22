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



export function Analytics() {

    const [expenses, setExpenses] = useState([]);
    const [incomes, setIncomes] = useState([]);
    const [labels, setLabels] = useState(['January', 'February', 'March']);

    const [sumIncomes, setSumIncomes] = useState(0);
    const [sumExpenses, setSumExpenses] = useState(0);



    const getData = async () => {
        const expenseList = await getExpensesAsDateList();
        const incomeList = await getIncomesAsDateList();
        const newLabels = await getTransactionDatesList();
    
        setIncomes(incomeList);
        setExpenses(expenseList);
        setLabels(newLabels);
    }

    const getIncomesAndExpenses = async () => { 
        const incomeList = await getIncomesAsDateList();
        const sumIncomes = incomeList.reduce((a, b) => {return a + b});
    
        const expenseList = await getExpensesAsDateList();
        const sumExpenses = expenseList.reduce((a, b) => {return a + b});
    
        setSumIncomes(sumIncomes);
        setSumExpenses(sumExpenses);
    };

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