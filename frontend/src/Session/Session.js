import { HttpInterface } from "./HttpInterface";

/**
 * Represents a login-session and contains the
 * JSON Web Token which where provided upon a
 * successful login.
 */
class Session {

    /**
     * Creates a new instance of Session.
     * 
     * @param {String} jwt The JWT received upon successful login.
     */
    constructor(jwt) {
        this.jwt = jwt;
        this.auth = false;
    }


    /**
     * Returns the JSON Web Token of the session.
     * 
     * @return JWT authentication Token.
     */
    getJwt() { 
        return String(this.jwt); 
    }


    /**
     * Returns a boolean indicating whether the session
     * is authenticated.
     * 
     * @return __true__ if authenticated, __false__ otherwise.
     */
    getAuth() { 
        return Boolean(this.auth); 
    }


    /**
     * Set the JSON Web Token of the session.
     * 
     * @param {String} jwt The received JWT.
     */
    setJwt(jwt) {
        this.jwt = jwt;
    }


    /**
     * Set the boolean indicating whether the session
     * is authenticated or not.
     * 
     * @param {Boolean} auth __true__ if authenticated, __false__ otherwise.
     */
    setAuth(auth) {
        this.auth = auth;
    }
}



/**
 * Represents the budget which the active user has
 * been provided with.
 */
class Budget {

    /**
     * Creates a new instance of Budget.
     * 
     * @param {Number} budgetId  The ID of the budget.
     * @param {String} startDate The starting date of the budget.
     * @param {String} endDate   The ending date of the budget.
     * @param {Number} boundary  The boundary of the budget.
     */
    constructor(budgetId, startDate, endDate, boundary) {
        this.budgetId = budgetId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.boundary = boundary;
    }


    /**
     * Returns the ID of the budget.
     * 
     * @return BudgetID.
     */
    getBudgetId() {
        return Number(this.budgetId);
    }


    /**
     * Returns the starting date of the budget.
     * 
     * @return Start date.
     */
    getStartDate() {
        return String(this.startDate);
    }


    /**
     * Returns the ending date of the budget.
     * 
     * @return End date.
     */
    getEndDate() {
        return String(this.endDate);
    }


    /**
     * Returns the boundary of the budget.
     * 
     * @return Budget boundary.
     */
    getBoundary() {
        return Number(this.boundary);
    }


    /**
     * Set the ID of the budget.
     * 
     * @param {Number} budgetId Budget ID.
     */
    setBudgetId(budgetId) {
        this.budgetId = budgetId;
    }


    /**
     * Set the starting date of the budget.
     * 
     * @param {String} startDate Budget starting date.
     */
    setStartDate(startDate) {
        this.startDate = startDate;
    }


    /**
     * Set the ending date of the budget.
     * 
     * @param {String} endDate Budget ending date.
     */
    setEndDate(endDate) {
        this.endDate = endDate;
    }


    /**
     * Set the boundary of the budget.
     * 
     * @param {Number} boundary Budget boundary.
     */
    setBoundary(boundary) {
        this.boundary = boundary;
    }
} 




/**
 * Represents a transaction made in a budget.
 * A transaction could be either an income or 
 * a expense. Expenses will have a negative number
 * in the value attribute.
 */
export class Transaction {

    /**
     * Creates a new instance of a transaction of Transaction.
     * 
     * @param {Number} transactionId The ID of the transaction.
     * @param {String} name The title of the transaction.
     * @param {Number} value The value of the transaction.
     * @param {String} description A short description of the transaction.
     * @param {String} date The date which the transaction was made.
     * @param {Number} budgetId The ID of the budget associated to the transaction.
     */
    constructor(transactionId, name, value, description, date, budgetId) {
        this.transactionId = transactionId;
        this.name = name;
        this.value = value
        this.description = description;
        this.date = date;
        this.budgetId = budgetId;
    }



    /**
     * Return the unique ID of the transaction.
     * 
     * @return TransactionID.
     */
    getTransactionId() { 
        return Number(this.transactionId);
    }


    /**
     * Return the title of the transaction.
     * 
     * @return The transaction name.
     */
    getName() { 
        return String(this.name);
    }



    /**
     * Return the value of the transaction.
     * 
     * @return Transaction value.
     */
    getValue() {
        return Number(this.value);
    }



    /**
     * Return the description text to the transaction.
     * 
     * @return Transaction description.
     */
    getDescription() {
        return String(this.description);
    }



    /**
     * Return the date of the transaction.
     * 
     * @return Transaction date as a string.
     */
    getDate() {
        return String(this.date);
    }



    /**
     * Return the ID of the associated budget.
     * 
     * @return The BudgetID of the transaction.
     */
    getBudgetId() {
        return Number(this.budgetId);
    }



    /**
     * Set the name of the transaction.
     * 
     * @param {String} name The name of the transaction.
     */
    setName(name) {
        this.name = name;
    }



    /**
     * Set the value of the transaction.
     * 
     * @param {Number} value New value of the transaction.
     */
    setValue(value) {
        this.value = value;
    }



    /**
     * Set the description of the transaction.
     * 
     * @param {String} description The description of the transaction.
     */
    setDescription(description) {
        this.description = description;
    }



    /**
     * Set the date of the transaction.
     * 
     * @param {String} date The date of the transaction.
     */
    setDate(date) {
        this.date = date;
    }
}




/**
 * Returns a list of all dates which a transaction has been
 * made in ascending order.
 * 
 * @return {Array} List of transaction dates.
 */
export async function getTransactionDatesList() {
    const dateList = await HttpInterface.fetchTransactionDates();
    return dateList;
}




/**
 * Returns an object which consists a list of all expenses
 * made, and a list with the corresponding dates of the
 * expenses.
 * 
 * @returns {Object} Object containing all expenses and corresponding dates.
 */
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




/**
 * Returns an object which consists a list of all incomes
 * made, and a list with the corresponding dates of the
 * incomes.
 * 
 * @returns {Object} Object containing all incomes and corresponding dates.
 */
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




/**
 * Returns an array containing the sum of expenses made on each
 * date which a transaction has been made.
 * 
 *  @returns {Array} List containing the sum of expenses made on each transaction-date.
 */
export async function getExpensesAsDateList() {
    const dateList = await getTransactionDatesList();
    let expDate = getExpensesAsDateObject();
    let transList = [];
    for (let day of dateList) { 
        if (expDate.dates.includes(day)) {
            transList.push(expDate.expenses[expDate.dates.indexOf(day)]);
        } else {
            transList.push(0);
        }
    }
    return transList;
}




/**
 * Returns an array containing the sum of incomes made on each
 * date which a transaction has been made.
 * 
 *  @returns {Array} List containing the sum of incomes made on each transaction-date.
 */
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





/** Instance of Session which are to hold the JWT. */
export const SESSION = new Session("");

/** Instance of Budget representing the user's budget. */
export const BUDGET = new Budget("", "", "", "");

/** Array of all transactions categorized as an expoense. */
export const EXPENSES = [];

/** Array of all transactions categorized as an income. */
export const INCOMES = [];