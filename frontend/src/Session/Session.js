import { HttpInterface } from "./HttpInterface";

class Session {

    constructor(jwt) {
        this.jwt = jwt;
        this.auth = false;
    }

    getJwt() { return this.jwt; }
    getAuth() { return this.auth; }
    setAuth(auth) { this.auth = auth; }
    setJwt(jwt) { this.jwt = jwt; }
}



class Budget {

    constructor(budgetId, startDate, endDate, boundary) {
        this.budgetId = budgetId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.boundary = boundary;
    }

    getBudgetId() { return this.budgetId; }
    getStartDate() { return this.startDate; }
    getEndDate() { return this.endDate; }
    getBoundary() { return this.boundary; }

    setBudgetId(budgetId) { this.budgetId = budgetId; }
    setStartDate(startDate) { this.startDate = startDate; }
    setEndDate(endDate) { this.endDate = endDate; }
    setBoundary(boundary) { this.boundary = boundary; }
} 



class Transaction {

    constructor(transactionId, name, value, description, date, budgetId) {
        this.transactionId = transactionId;
        this.name = name;
        this.value = value
        this.description = description;
        this.date = date;
        this.budgetId = budgetId;
    }

    getTransactionId() { return this.transactionId; }
    getName() { return this.name; }
    getValue() { return this.value; }
    getDescription() { return this.description; }
    getDate() { return this.date; }
    getBudgetId() { return this.budgetId; }

    setName(name) { this.name = name; }
    setValue(value) { this.value = value; }
    setDescription(description) { this.description = description; }
    setDate(date) { this.date = date; }
}



const SESSION = new Session("");
const BUDGET = new Budget("", "", "", "");
const EXPENSES = [];
const INCOMES = [];

export async function getTransactionDatesList() {
    const dateList = await HttpInterface.fetchTransactionDates();
    return dateList;
}

export function getExpensesAsDateObject() {
    let dateList = [];
    let transList = [];
    for (let trans of EXPENSES) {
        let val = (-1) * trans.value;
        if (dateList.includes(trans.date)) {
            transList[dateList.indexOf(trans.date)] += (val);
        } else {
            transList.push(val);
            dateList.push(trans.date);
        }
    }
    return {dates: dateList, expenses: transList};
}

export function getIncomesAsDateObject() {
    let dateList = [];
    let transList = [];
    for (let trans of INCOMES) {
        if (dateList.includes(trans.date)) {
            transList[dateList.indexOf(trans.date)] += trans.value;
        } else {
            transList.push(trans.value);
            dateList.push(trans.date);
        }
    }
    return {dates: dateList, incomes: transList};
}

export async function getExpensesAsDateList() {
    const dateList = await getTransactionDatesList();
    let expDate = getExpensesAsDateObject();
    let transList = [];
    for (let day of dateList) { // Burde kanskje hente transactiondates sammen med budget og heller hente det der i fra for å slippe promise problemet
        if (expDate.dates.includes(day)) {
            transList.push(expDate.expenses[expDate.dates.indexOf(day)]);
        } else {
            transList.push(0);
        }
    }
    return transList;
}


export async function getIncomesAsDateList() {
    const dateList = await getTransactionDatesList();
    let incDate = getIncomesAsDateObject();
    let transList = [];
    for (let day of dateList) {
        if (incDate.dates.includes(day)) {
            transList.push(incDate.incomes[incDate.dates.indexOf(day)]);
        } else {
            transList.push(0);
        }
    }
    return transList;
}

export { 
    SESSION, 
    BUDGET, 
    EXPENSES,
    INCOMES,
    Transaction 
};