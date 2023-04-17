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
  SERVER_URL: "localhost:8090/api",

  /**
   * Requests the budget instance which belongs
   * to a given user.
   *
   * @return representation of the user's budget.
   */
  fetchBudget: function () {
    let budget;

    fetch(this.SERVER_URL + "/budget")
      .then((response) => response.json())
      // eslint-disable-next-line no-undef
      .then((budget = data._embedded.startDate))
      .catch((err) => console.error(err));

    return budget;
  },

  /**
   * Requests all transactions labeled as an
   * expense to the given budget.
   *
   * @return collection of expense-labeled transactions.
   */
  fetchAllExpenses: function () {
    fetch(this.SERVER_URL + "/budget/{id}/expenses")
      .then((response) => response.json())
      .then()
      .catch((err) => console.error(err));

    return null;
  },

  /**
   * Requests all transactions labeled as an
   * income to the given budget.
   *
   * @return collection of income-labeled transactions.
   */
  fetchAllIncomes: function () {
    fetch(this.SERVER_URL + "/budget/{id}/incomes")
      .then((response) => response.json())
      .then()
      .catch((err) => console.error(err));

    return null;
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
  authenticateLogin: function () {},

  /**
   * Registers a new user with the credentials
   * provided by the user.
   *
   * @return `true` if sign-up was successful.
   */
  createNewUser: function () {},
};
