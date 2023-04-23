import * as CONFIG from '../config';
import { 
    SESSION, 
    BUDGET, 
    EXPENSES, 
    INCOMES, 
    Transaction 
} from './Session';


/** URL of the server which delivers the API. */
const SERVER_URL = "http://localhost:8090";



/**
 * __HTTP Interface__
 *
 * Acts as an interface which provides all communications
 * which are forwarded to the server. Rather than having
 * different components be responsible for their own requests
 * through the API, the interface is designed with the intention
 * to acts as an intermediary between the client and the server.
 *
 * @since   17.04.2023
 * @version 17.04.2023
 * @author  Group 19
 */
export const HttpInterface = {

    /**
     * Fetches the budget instance which belongs
     * to a given user.
     * 
     * @param {Number} bid The ID of the Budget.
     */
    fetchBudget: async function (bid) {

        await fetch(SERVER_URL + "/api/budgets/" + bid,
        {
            method: 'GET',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + SESSION.getJwt()
            }
        })
        .then(response => {
            if (response.ok) return response.text(); 
            else if (CONFIG.SHOW_BUDGET_FAILURE_ALERT) window.alert("Incomes request FAILED.");
        })
        .then(function (data) {
            const responseBody = JSON.parse(data);

            BUDGET.setBudgetId(bid);
            BUDGET.setStartDate(responseBody.startDate);
            BUDGET.setEndDate(responseBody.endDate);
            BUDGET.setBoundary(responseBody.boundary);

            if (CONFIG.SHOW_AUTHENTICATION_BUDGET_ALERT) {
                window.alert(
                    "Budget ID: " + BUDGET.getBudgetId() +
                    "\nStart: "  + BUDGET.getStartDate() +
                    "\nEnd: "    + BUDGET.getEndDate() +
                    "\nBoundary " + BUDGET.getBoundary()
                );
            }
        })
        .catch(err => console.error(err));

        this.fetchAllExpenses();
        this.fetchAllIncomes();
    },



    /**
     * Fetches all transactions labeled as an expense to the given budget. 
     * The /GET request is forwarded to {@link SERVER_URL}, which is where
     * the RESTful services are implemented in the back-end.
     */
    fetchAllExpenses: function () {
        fetch(SERVER_URL + "/transactions/expensesById/" + BUDGET.getBudgetId(),
        {
            method: 'GET',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + SESSION.getJwt()
            }
        })
        .then(response => {
            if (response.ok) return response.text(); 
            else if (CONFIG.SHOW_TRANSACTION_FAILURE_ALERT) window.alert("Expenses request FAILED.");
        })
        .then(function (data) {
            const body = JSON.parse(data);

            for (let element of body) {
                let ex = new Transaction(
                    element.tid,
                    element.tname,
                    element.value,
                    element.description,
                    element.date,
                    Number(BUDGET.getBudgetId())
                );
                EXPENSES.push(ex)
            }
            if (CONFIG.SHOW_TRANSACTION_FETCHING_ALERT) {
                alert("Number of expenses: " + EXPENSES.length);
            }
        })
        .catch(err => console.error(err));
    },



    /**
     * Fetches all expenses associated to the budget in 
     * ascending order by date. The /GET request is forwarded
     * to the /api endpoint, which will return each expense
     * along with the href attribute for each transaction.
     */
    fetchAllExpenses2: async function () {

        const link = SERVER_URL + "/api/transactions/search/getExpensesByBudgetIdOrderByDateAsc?budgetId=";
       
        const result = await fetch(link + BUDGET.getBudgetId(),
        {
            method: 'GET',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + SESSION.getJwt()
            }
        })
        .then(response => response.json())
        .then(data => {return data._embedded.transactions})
        .catch(err => console.error(err));

        return result;
    },



    /**
     * Fetches all income associated to the budget in 
     * ascending order by date. The /GET request is forwarded
     * to the /api endpoint, which will return each expense
     * along with the href attribute for each transaction.
     */
    fetchAllIncomes2: async function () {

        const link = SERVER_URL + "/api/transactions/search/getIncomesByBudgetIdOrderByDateAsc?budgetId=";
       
        const result = await fetch(link + BUDGET.getBudgetId(),
        {
            method: 'GET',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + SESSION.getJwt()
            }
        })
        .then(response => response.json())
        .then(data => {return data._embedded.transactions})
        .catch(err => console.error(err));

        return result;
    },



    /**
     * Fetches all transactions labeled as an income to the given budget. 
     * The /GET request is forwarded to {@link SERVER_URL}, which is where
     * the RESTful services are implemented in the back-end.
     */
    fetchAllIncomes: function () {
        fetch(SERVER_URL + "/transactions/incomesById/" + BUDGET.getBudgetId(),
        {
            method: 'GET',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + SESSION.getJwt()
            }
        })
        .then(response => {
            if (response.ok) return response.text(); 
            else if (CONFIG.SHOW_TRANSACTION_FAILURE_ALERT) window.alert("Incomes request FAILED.");
        })
        .then(function (data) {
            const body = JSON.parse(data);

            for (let element of body) {
                let ex = new Transaction(
                    element.tid,
                    element.tname,
                    element.value,
                    element.description,
                    element.date,
                    Number(BUDGET.getBudgetId())
                );
                INCOMES.push(ex)
            }
            if (CONFIG.SHOW_TRANSACTION_FETCHING_ALERT) {
                alert("Number of incomes: " + INCOMES.length);
            }
        })
        .catch(err => console.error(err));
    },



    /**
     * Fetches the transaction dates from the back-end implementation. The response
     * is a list which contains string representations of all the dates which a 
     * transaction, either income or expense, has been made in the given budget in ascending order.
     */
    fetchTransactionDates: async function() {
        const result = await fetch(SERVER_URL + "/transactions/budgetTransactionDates/" + BUDGET.getBudgetId(),
        {
            method: 'GET',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + SESSION.getJwt()
            }
        })
        .then(response => response.json())
        .then(data => {return data; })
        .catch(err => console.error(err));

        return result;
    },



    /**
     * Adds a new transaction categorized to the given budget.
     * The /POST request is forwarded to the server, which uploads
     * the new transaction to the database.
     */
    addTransaction: async function (trans) {
        const result = await fetch(SERVER_URL + "/transactions",
        {
            method: 'POST',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + SESSION.getJwt()
            },
            body: JSON.stringify(trans)
        })
        .then(response => {
            if (!response.ok) {
                alert("Something went wrong!");
            } 
        })
        .catch(err => window.alert(err));
    },



    /**
     * Updates an existing transaction to the given budget.
     * The /PUT
     *
     * @return `true` if transaction was updated successfully.
     */
    updateTransaction: async function (id, newTransaction) {
        const result = await fetch(SERVER_URL + "/transactions/" + id,
        {
            method: 'PUT',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + SESSION.getJwt()
            },
            body: JSON.stringify(newTransaction)
        })
        .then(response => {
            if (!response.ok) {
                alert("Something went wrong!");
            } 
        })
        .catch(err => window.alert(err));
    },



    /**
     * Removes a given transaction from the budget.
     *
     * @return `true` if transaction was removed successfully.
     */
    deleteTransaction: async function (id) {
        const result = await fetch(SERVER_URL + "/transactions/" + id,
        {
            method: 'DELETE',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + SESSION.getJwt()
            }
        })
        .then(response => {
            if (!response.ok) {
                alert("Something went wrong!");
            } 
        })
        .catch(err => window.alert(err));
    },



    /**
     * Updates the properties of the given budget.
     *
     * @return `true` if date was updated successfully.
     */
    updateBudget: async function () {

        const newBudget = {
            startDate: BUDGET.startDate,
            endDate: BUDGET.endDate,
            boundary: Number(BUDGET.boundary)
        }

        const result = await fetch(SERVER_URL + "/budgets/" + BUDGET.getBudgetId(),
        {
            method: 'PUT',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + SESSION.getJwt()
            },
            body: JSON.stringify(newBudget)
        })
        .then(response => {
            if (response.ok) {
                window.alert(
                    "Budget has been updated.\nMargin: " 
                    + BUDGET.getBoundary() + "\nStart date: "
                    + BUDGET.getStartDate() + "\nEnd date: "
                    + BUDGET.getEndDate()
                );
            } else {
                alert("Something went wrong!");
            }
        })
        .catch(err => window.alert(err));
    },



    /**
     * Authenticates a session and validates the login-
     * credentials provided by the user.
     *
     * @return `true` if user was found.
     */
    authenticateLogin: async function (credentials) {

        /** True if the user is authenticated. */
        let authenticated = false;
        
       
        let response = "";
        response = await fetch(SERVER_URL + '/login',
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(credentials)
            })
            .then(response => {

                if (response.ok) {
                    const auth = response.headers.get("Authorization").split(' ');
                    const jwt = auth[1].split(',')[0];
                    const bid = Number(auth[3]);

                    SESSION.setJwt(jwt);
                    SESSION.setAuth(true);
                    this.fetchBudget(bid);
                    authenticated = true;
                } else if (CONFIG.SHOW_AUTHENTICATION_FAILURE_ALERT) {
                    window.alert("Bad credentials");
                }
            })
            .then(authenticated => {return authenticated;})
            .catch(err => console.error(err));
        return authenticated;
    },



    /**
     * Registers a new user with the credentials
     * provided by the user.
     *
     * @return `true` if sign-up was successful.
     */
    signUp: async function (credentials) {
        const username = credentials.username;
        const password = credentials.password;

        const result = await fetch(SERVER_URL + "/users",
        {
            method: 'POST',
            headers: { 
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(credentials)
        })
        .then(async response => {
            if (!response.ok) {
                alert("Username already exists, please try a different one.");
            } else {
                const loginInfo = {
                    username: username,
                    password: password
                };
        
                const login = await HttpInterface.authenticateLogin(loginInfo);
            }
        })
        .catch(err => window.alert(err));

        return result;
    },
};