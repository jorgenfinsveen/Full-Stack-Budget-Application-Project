package no.idata1002.group19.web.api;

import no.idata1002.group19.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import no.idata1002.group19.domain.entity.Budget;

import java.util.List;

/**
 * Represents an Budget Controller class that is a RESTful
 * controller that handles the HTTP requests.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    /**
     * Retrieves a list of all budgets from the TransactionService and returns them as an HTTP response.
     *
     * @return ResponseEntity<List<Budget>> - an HTTP response containing a list of all budgets
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Budget>> getBudgets() {
        return ResponseEntity.ok((List<Budget>) this.budgetService.getAll());
    }

    /**
     * Retrieves a budget from BudgetService and returns them as an HTTP response
     *
     * @param id of the budget that you want to get
     * @return ResponseEntity.ok - and HTTP response containing the budget.
     */
    @GetMapping("/{id}")
    public ResponseEntity getBudgetById(@PathVariable long id) {
        return ResponseEntity.ok(this.budgetService.findById(id));
    }

    /**
     * Adds a new budget to the system using the BudgetService and returns an appropriate HTTP response.
     *
     * @param budget - the budget object representing the budget to add.
     * @return ResponseEntity - an HTTP response indicating whether the budget was added successfully.
     */
    @PostMapping("/add")
    public ResponseEntity addBudget(@RequestBody Budget budget) {
        if(!this.budgetService.add(budget)) {
            return new ResponseEntity("Budget was not added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("Budget was added", HttpStatus.CREATED);
    }

    /**
     * Updates a budget with the specified ID using the BudgetService and returns an appropriate HTTP response.
     *
     * @param id - the ID of the budget to update.
     * @param budget - the budget object representing the updated budget.
     * @return ResponseEntity - an HTTP response indicating whether the budget was updated successfully.

     */
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody Budget budget) {
        Budget oldBudget = this.budgetService.findById(id);
        if (oldBudget == null) {
            return new ResponseEntity("didn't find budget", HttpStatus.NOT_FOUND);
        }
        this.budgetService.update(id, budget);
        if (this.budgetService.findById(id) == null) {
            return new ResponseEntity("Budget didn't update", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("Budget was updated", HttpStatus.OK);
    }

    /**
     * Deletes a budget with the specified ID using the BudgetService and returns an appropriate HTTP response.
     * @param id - the ID of the budget to delete.
     * @return ResponseEntity - an HTTP response indicating whether the budget was deleted successfully.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        if (!this.budgetService.delete(id)) {
            return new ResponseEntity("Budget was not removed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("Budget was removed", HttpStatus.OK);
    }
}
