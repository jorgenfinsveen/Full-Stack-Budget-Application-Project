import { SESSION, BUDGET, EXPENSES, INCOMES, Transaction } from './Session';

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
const HttpInterface = {
    /** URL of the server which delivers the API. */
    SERVER_URL: "http://localhost:8090/api",

    /** URL for authentication. */
    AUTH_URL: "http://localhost:8090",

    /**
     * Requests the budget instance which belongs
     * to a given user.
     *
     * @return representation of the user's budget.
     */
    fetchBudget: async function (bid) {
        var successful = false;

        await fetch(this.SERVER_URL + "/budgets/" + bid,
        {
            method: 'GET',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + SESSION.getJwt()
            }
        })
        .then(response => {
            if (response.ok) return response.text(); 
            else window.alert("Budget request FAILED.");
        })
        .then(function (data) {
            const responseBody = JSON.parse(data);

            BUDGET.setBudgetId(bid);
            BUDGET.setStartDate(responseBody.startDate);
            BUDGET.setEndDate(responseBody.endDate);
            BUDGET.setBoundary(responseBody.boundary);

            window.alert(
                "Budget ID: " + BUDGET.getBudgetId() +
                "\nStart: "  + BUDGET.getStartDate() +
                "\nEnd: "    + BUDGET.getEndDate() +
                "\nBoundary " + BUDGET.getBoundary()
            );
        })
        .catch(err => console.error(err));

        this.fetchAllExpenses();
        this.fetchAllIncomes();
    },

    /**
     * Requests all transactions labeled as an
     * expense to the given budget.
     *
     * @return collection of expense-labeled transactions.
     */
    fetchAllExpenses: function () {
        fetch(this.SERVER_URL + "/transactions/search/getExpensesByBudgetId?budgetId=" + BUDGET.getBudgetId(),
        {
            method: 'GET',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + SESSION.getJwt()
            }
        })
        .then(response => {
            if (response.ok) return response.text(); 
            else window.alert("Expenses request FAILED.");
        })
        .then(function (data) {
            const body = JSON.parse(data);
            const _embedded = body._embedded;
            const expenses = _embedded.transactions;
            
            EXPENSES.length = 0;

            for (var element in expenses) {
                var ex = new Transaction(
                    "",
                    element.tname,
                    element.value,
                    element.description,
                    element.date,
                    BUDGET.getBudgetId()
                );
                EXPENSES.push(ex)
            }
            alert("Number of expenses: " + EXPENSES.length);
        })
        .catch(err => console.error(err));
    },

    /**
     * Requests all transactions labeled as an
     * income to the given budget.
     *
     * @return collection of income-labeled transactions.
     */
    fetchAllIncomes: function () {
        fetch(this.SERVER_URL + "/transactions/search/getIncomesByBudgetId?budgetId=" + BUDGET.getBudgetId(),
        {
            method: 'GET',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + SESSION.getJwt()
            }
        })
        .then(response => {
            if (response.ok) return response.text(); 
            else window.alert("Incomes request FAILED.");
        })
        .then(function (data) {
            const body = JSON.parse(data);
            const _embedded = body._embedded;
            const incomes = _embedded.transactions;
            
            INCOMES.length = 0;

            for (var element in incomes) {
                var ex = new Transaction(
                    "",
                    element.tname,
                    element.value,
                    element.description,
                    element.date,
                    BUDGET.getBudgetId()
                );
                INCOMES.push(ex)
            }
            alert("Number of incomes: " + INCOMES.length);
        })
        .catch(err => console.error(err));
    },

    /**
     * Requests all transactions labeled as an
     * expense to the given budget at a certain date.
     *
     * @return collection of expense-labeled transactions.
     */
    fetchExpensesAt: function () {
        fetch(this.SERVER_URL + "/budget/{id}/expenses/{date}")
            .then((response) => response.json())
            .then()
            .catch((err) => console.error(err));

        return null;
    },

    /**
     * Requests all transactions labeled as an
     * income to the given budget at a certain date.
     *
     * @return collection of income-labeled transactions.
     */
    fetchIncomesAt: function () {
        fetch(this.SERVER_URL + "/budget/{id}/incomes/{date}")
            .then((response) => response.json())
            .then()
            .catch((err) => console.error(err));

        return null;
    },

    /**
     * Adds a new transaction categorized as an
     * expense to the given budget.
     *
     * @return `true` if transaction was added successfully.
     */
    addExpense: function () {},

    /**
     * Adds a new transaction categorized as an
     * income to the given budget.
     *
     * @return `true` if transaction was added successfully.
     */
    addIncome: function () {},

    /**
     * Updates an existing transaction categorized as an
     * expense to the given budget.
     *
     * @return `true` if transaction was updated successfully.
     */
    updateExpense: function () {},

    /**
     * Updates an existing transaction categorized as an
     * income to the given budget.
     *
     * @return `true` if transaction was updated successfully.
     */
    updateIncome: function () {},

    /**
     * Removes a new transaction categorized as an
     * expense to the given budget.
     *
     * @return `true` if transaction was removed successfully.
     */
    deleteExpense: function () {},

    /**
     * Removes an existing transaction categorized as an
     * income to the given budget.
     *
     * @return `true` if transaction was removed successfully.
     */
    deleteIncome: function () {},

    /**
     * Updates the starting date of a given budget.
     *
     * @return `true` if date was updated successfully.
     */
    setBudgetStart: function () {},

    /**
     * Updates the ending date of a given budget.
     *
     * @return `true` if date was updated successfully.
     */
    setBudgetEnd: function () {},

    /**
     * Updates the financial goal of a given budget.
     *
     * @return `true` if goal was updated successfully.
     */
    setBudgetGoal: function () {},

    /**
     * Authenticates a session and validates the login-
     * credentials provided by the user.
     *
     * @return `true` if user was found.
     */
    authenticateLogin: function (credentials) {

        fetch(this.AUTH_URL + '/login',
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(credentials)
            })
            .then(response => {

                if (response.ok) {
                    const auth = response.headers.get("Authorization");
                    const jwt = auth.slice(7, auth.length - 12);
                    const bid = auth.slice(auth.length - 1, auth.length);

                    SESSION.setJwt(jwt);
                    this.fetchBudget(bid);
                } else {
                    window.alert("Bad credentials");
                }
            })
            .catch(err => console.error(err));
    },

    /**
     * Registers a new user with the credentials
     * provided by the user.
     *
     * @return `true` if sign-up was successful.
     */
    createNewUser: function () {},
};

export { HttpInterface };