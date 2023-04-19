package no.idata1002.group19.web.api;

import no.idata1002.group19.domain.entity.Transaction;
import no.idata1002.group19.domain.repository.TransactionRepository;
import no.idata1002.group19.service.TransasctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for transaction entity.
 * Controls the endpoints for transaction
 */
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransasctionService transasctionService;

    @Autowired
    TransactionRepository repository;

    /**
     * Retrieves a list of all transaction from the TransactionService and returns them as an HTTP response.
     *
     * @return ResponseEntity<List<Transaction>> - an HTTP response containing a list of all transaction
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Transaction>> getTransaction() {
        return ResponseEntity.ok((List<Transaction>) this.transasctionService.getAll());
    }

    @GetMapping("/expenses={id}")
    public Iterable<Transaction> getExpensesById(@PathVariable int id) {
        return repository.getExpensesByBudgetId(id);
    }

    /**
     * Retrieves a transaction from TransactionService and returns them as an HTTP response
     *
     * @param id of the transaction that you want to get
     * @return ResponseEntity.ok - and HTTP response containing the transaction.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable long id) {
        return ResponseEntity.ok(this.transasctionService.findById(id));
    }

    /**
     * Adds a new transaction to the system using the TransactionService and returns an appropriate HTTP response.
     *
     * @param transaction - the transaction object representing the transaction to add.
     * @return ResponseEntity - an HTTP response indicating whether the transaction was added successfully.
     */
    @PostMapping("/add")
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
        if(!this.transasctionService.add(transaction)) {
            return new ResponseEntity<>("Transaction was not added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Transaction was added", HttpStatus.CREATED);
    }

    /**
     * Updates a transaction with the specified ID using the TransactionService and returns an appropriate HTTP response.
     *
     * @param id - the ID of the transaction to update.
     * @param transaction - the Transaction object representing the updated Transaction.
     * @return ResponseEntity - an HTTP response indicating whether the transaction was updated successfully.

     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody Transaction transaction) {
        Transaction oldTransaction = this.transasctionService.findById(id);
        if (oldTransaction == null) {
            return new ResponseEntity<>("didn't find transaction", HttpStatus.NOT_FOUND);
        }
        this.transasctionService.update(id, transaction);
        if (this.transasctionService.findById(id) == null) {
            return new ResponseEntity<>("Transaction didn't update", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Transaction was updated", HttpStatus.OK);
    }

    /**
     * Deletes a transaction with the specified ID using the TransactionService and returns an appropriate HTTP response.
     * @param id - the ID of the transaction to delete.
     * @return ResponseEntity - an HTTP response indicating whether the transaction was deleted successfully.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        if (!this.transasctionService.delete(id)) {
            return new ResponseEntity<>("Transaction was not removed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Transaction was removed", HttpStatus.OK);
    }
}
