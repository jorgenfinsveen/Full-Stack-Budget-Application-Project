
class Session {

    constructor(jwt) {
        this.jwt = jwt;
    }

    getJwt() { return this.jwt; }
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



var SESSION = new Session("");
var BUDGET = new Budget("", "", "", "");
var EXPENSES = [];
var INCOMES = [];

export { 
    SESSION, 
    BUDGET, 
    EXPENSES,
    INCOMES,
    Transaction 
};