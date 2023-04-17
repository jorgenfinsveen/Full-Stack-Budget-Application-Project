package no.idata1002.group19.service;

import no.idata1002.group19.domain.entity.Budget;
import no.idata1002.group19.domain.entity.Transaction;
import no.idata1002.group19.domain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Represent the service class for transaction
 */
@Service
public class TransasctionService {

    @Autowired
    TransactionRepository transactionRepository;

    /**
     * Returns all transactions
     * @return transactions
     */
    public Iterable<Transaction> getAll() {
        return this.transactionRepository.findAll();
    }

    /**
     * Return a transaction from a given id
     * @param id of the transaction that you want to find
     * @return transaction
     */
    public Transaction findById(long id) {
        return this.transactionRepository.findById(id).orElse(null);
    }

    /**
     * Adds a transaction to the repository.
     * @param transaction the transaction that you want to add
     * @return boolean statement. True if it was added, false if not.
     */
    public boolean add(Transaction transaction) {
        boolean added = false;
        if(caBeAdded(transaction)) {
            this.transactionRepository.save(transaction);
            added = true;
        }
        return added;
    }

    /**
     * Checks if the transaction can be added to the repository
     * @param transaction the transaction that you want to check
     * @return boolean statement. True if it can be added, false if not.
     */
    private boolean caBeAdded(Transaction transaction) {
        return transaction != null && transaction.isValid();
    }

    /**
     * Removes a transaction from the repository
     * @param id of the transaction that you want to remove
     * @return boolean statement. True if it was removed, false if not.
     */
    public boolean delete(long id) {
        boolean deleted = false;
        if(findById(id) != null) {
            this.transactionRepository.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

    /**
     * Update an existing transaction.
     * @param id of the transaction that you want to update.
     * @param transaction what you want the old transaction to be updated to.
     */
    public void update(long id, Transaction transaction) {
        Transaction existingTransaction = findById(id);
        String errorMessage = null;
        if (existingTransaction == null) {
            errorMessage = "No transaction exists with the id " + id;
        }
        if (transaction == null || !transaction.isValid()) {
            errorMessage = "Wrong data in request body";
        }
        else if(transaction.getTid() != id) {
            errorMessage = "The ID of the transaction in the URL does not match anny ID in the JSON data";
        }
        if (errorMessage == null) {
            this.transactionRepository.save(transaction);
        }
    }
}
