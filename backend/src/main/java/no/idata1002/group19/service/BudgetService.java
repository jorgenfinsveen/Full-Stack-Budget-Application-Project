package no.idata1002.group19.service;

import no.idata1002.group19.domain.entity.Budget;
import no.idata1002.group19.domain.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class represent the service class for budget entity
 */
@Service
public class BudgetService {

    @Autowired
    BudgetRepository budgetRepository;

    /**
     * Returns all budgets
     * @return budgets
     */
    public Iterable<Budget> getAll() {
        return this.budgetRepository.findAll();
    }

    /**
     * Return budget from a given id
     * @param id of the budget you want to find
     * @return budget.
     */
    public Budget findById(long id) {
        return this.budgetRepository.findById(id).orElse(null);
    }

    /**
     * Adds a budget to the repository
     * @param budget the budget you want to add
     * @return boolean statement. True if added, false if not
     */
    public boolean add(Budget budget) {
        boolean added = false;
        if(canBeAdded(budget)) {
            this.budgetRepository.save(budget);
            added = true;
        }
        return added;
    }

    /**
     * Checks if the budget can be added to the repository.
     * @param budget the budget you want to check
     * @return boolean statement. True if it can be added, false if not
     */
    private boolean canBeAdded(Budget budget) {
        return budget !=null && budget.isValid();
    }

    /**
     * Removes a budget from the repository
     * @param id of the budget that you want to remove
     * @return boolean statement. True if it was remove, false if not.
     */
    public boolean delete(long id) {
        boolean deleted = false;
        if(findById(id) !=null) {
            this.budgetRepository.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

    /**
     * Update an existing budget.
     * @param id of the budget you want to update
     * @param budget what you want the old budget to be updated to.
     */
    public void update(long id, Budget budget) {
        Budget existingBudget = findById(id);
        String errorMessage = null;
        if (existingBudget == null) {
            errorMessage = "No budget exists with the id " + id;
        }
        if (budget == null || !budget.isValid()) {
            errorMessage = "Wrong data in request body";
        }
        else if(budget.getBid() != id) {
            errorMessage = "The ID of the user in the URL does not match anny ID in the JSON data";
        }
        if (errorMessage == null) {
            this.budgetRepository.save(budget);
        }
    }
}
