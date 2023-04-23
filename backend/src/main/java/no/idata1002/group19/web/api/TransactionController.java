package no.idata1002.group19.web.api;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import no.idata1002.group19.domain.entity.Budget;
import no.idata1002.group19.domain.entity.Transaction;
import no.idata1002.group19.domain.entity.TransactionCredentials;
import no.idata1002.group19.domain.repository.BudgetRepository;
import no.idata1002.group19.domain.repository.TransactionRepository;

/**
 * Controller class for transaction entity.
 * Controls the endpoints for transaction
 * 
 * @author Group19
 * @since 16.04.2023
 * @version 16.04.2023
 */
@RestController
public class TransactionController {

    @Autowired
    TransactionRepository repository;

    @Autowired
    BudgetRepository budgetRepository;

    /**
     * Retrieves a list of all transaction from the TransactionService and returns
     * them as an HTTP response.
     *
     * @return ResponseEntity<List<Transaction>> - an HTTP response containing a
     *         list of all transaction
     */
    @GetMapping("/transactions")
    public Iterable<Transaction> getTransactions() {
        return repository.findAll();
    }

    /**
     * Retrieves a list of all transaction from the TransactionService and returns
     * them as an HTTP response.
     *
     * @return ResponseEntity<List<Transaction>> - an HTTP response containing a
     *         list of all transaction
     */
    @GetMapping("/transactions/expensesById/{id}")
    public Iterable<Transaction> getExpensesById(@PathVariable String id) {
        return repository.getExpensesByBudgetIdOrderByDateAsc(Integer.parseInt(id));
    }

    /**
     * Retrieves a list of all incomes from a budget with the specified id from the
     * TransactionService and returns them as an HTTP response.
     *
     * @param id - the ID of the budget to get incomes from.
     * @return ResponseEntity<List<Transaction>> - an HTTP response containing a
     *         list of all incomes from the budget.
     */
    @GetMapping("/transactions/incomesById/{id}")
    public Iterable<Transaction> getIncomesById(@PathVariable String id) {
        return repository.getIncomesByBudgetIdOrderByDateAsc(Integer.parseInt(id));
    }

    /**
     * Retrieves a transaction from TransactionService and returns them as an HTTP
     * response
     *
     * @param id of the transaction that you want to get
     * @return ResponseEntity.ok - and HTTP response containing the transaction.
     */
    @GetMapping("/transactions/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable long id) {
        ResponseEntity<?> response;
        Optional<Transaction> opt = repository.findById(id);

        if (opt.isPresent()) {
            response = ResponseEntity.ok(opt.get());
        } else {
            response = (ResponseEntity<?>) ResponseEntity.notFound();
        }
        return response;
    }

    /**
     * Adds a new transaction to the system using the TransactionService and returns
     * an appropriate HTTP response.
     *
     * @param transaction - the transaction object representing the transaction to
     *                    add.
     * @return ResponseEntity - an HTTP response indicating whether the transaction
     *         was added successfully.
     */
    @PostMapping("/transactions")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionCredentials credentials) {
        ResponseEntity<?> response;
        Optional<Budget> budget = budgetRepository.findById(credentials.getBid());

        if (budget.isPresent()) {
            Transaction transaction = new Transaction(
                    credentials.getName(),
                    credentials.getValue(),
                    credentials.getDescription(),
                    LocalDate.parse(credentials.getDate()),
                    budget.get());
            repository.save(transaction);
            response = new ResponseEntity<>("Transaction was added", HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>("Budget was not found.", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Updates a transaction with the specified ID using the TransactionService and
     * returns an appropriate HTTP response.
     *
     * @param id          - the ID of the transaction to update.
     * @param transaction - the Transaction object representing the updated
     *                    Transaction.
     * @return ResponseEntity - an HTTP response indicating whether the transaction
     *         was updated successfully.
     */
    @PutMapping("/transactions/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody TransactionCredentials credentials) {
        String name = credentials.getName();
        int value = credentials.getValue();
        String description = credentials.getDescription();
        LocalDate date = LocalDate.parse(credentials.getDate());

        repository.updateTransaction(id, date, description, name, value);

        return new ResponseEntity<>("Budget was updated", HttpStatus.OK);
    }

    /**
     * Deletes a transaction with the specified ID using the TransactionService and
     * returns an appropriate HTTP response.
     * 
     * @param id - the ID of the transaction to delete.
     * @return ResponseEntity - an HTTP response indicating whether the transaction
     *         was deleted successfully.
     */
    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        ResponseEntity<?> response;
        Optional<Transaction> opt = repository.findById(id);

        if (opt.isPresent()) {
            Transaction transaction = opt.get();
            repository.delete(transaction);
            response = new ResponseEntity<>("Transaction was removed", HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("Transaction was not found", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * 
     * Retrieves a list of all unique transaction dates for a specific budget from
     * TransactionRepository and returns them as an HTTP response.
     * 
     * @param id - the ID of the budget to retrieve transaction dates for.
     * @return ResponseEntity - an HTTP response containing a list of all unique
     *         transaction dates for the specified budget.
     */
    @GetMapping("/transactions/budgetTransactionDates/{id}")
    public Iterable<String> getDates(@PathVariable long id) {
        return repository.getTransactionsDatesByBudgetIdOrderByDateAsc(id);
    }
}
