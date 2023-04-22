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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.idata1002.group19.domain.entity.Budget;
import no.idata1002.group19.domain.entity.BudgetCredentials;
import no.idata1002.group19.domain.repository.BudgetRepository;

/**
 * Represents an Budget Controller class that is a RESTful
 * controller that handles the HTTP requests.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetRepository repository;

    /**
     * Retrieves a list of all budgets from the TransactionService and returns them as an HTTP response.
     *
     * @return ResponseEntity<List<Budget>> - an HTTP response containing a list of all budgets
     */
    @GetMapping("")
    public Iterable<Budget> getBudgets() {
        return repository.findAll();
    }


    /**
     * Adds a new budget to the system using the BudgetService and returns an appropriate HTTP response.
     *
     * @param budget - the budget object representing the budget to add.
     * @return ResponseEntity - an HTTP response indicating whether the budget was added successfully.
     */
    @PostMapping("")
    public ResponseEntity<?> addBudget(@RequestBody BudgetCredentials credentials) {
        Budget budget = new Budget(
            LocalDate.parse(credentials.getStartDate()),
            LocalDate.parse(credentials.getEndDate()),
            credentials.getBoundary()
        );
        repository.save(budget);
        return new ResponseEntity<>("Budget was added", HttpStatus.CREATED);
    }

    
    /**
     * Updates a budget with the specified ID using the BudgetService and returns an appropriate HTTP response.
     *
     * @param id - the ID of the budget to update.
     * @param budget - the budget object representing the updated budget.
     * @return ResponseEntity - an HTTP response indicating whether the budget was updated successfully.

     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody BudgetCredentials credentials) {
        ResponseEntity<String> response;
        LocalDate start = LocalDate.parse(credentials.getStartDate());
        LocalDate end = LocalDate.parse(credentials.getEndDate());
        int bound = credentials.getBoundary();
        repository.updateBudget(id, start, end, bound);
        response = new ResponseEntity<>("Budget was updated", HttpStatus.OK);
        return response;
    }


    /**
     * Deletes a budget with the specified ID using the BudgetService and returns an appropriate HTTP response.
     * @param id - the ID of the budget to delete.
     * @return ResponseEntity - an HTTP response indicating whether the budget was deleted successfully.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        ResponseEntity<String> response;
        Optional<Budget> opt = repository.findById(id);

        if (opt.isPresent()) {
            repository.delete(opt.get());
            response = new ResponseEntity<>("Budget was removed", HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("Budget was not found", HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
