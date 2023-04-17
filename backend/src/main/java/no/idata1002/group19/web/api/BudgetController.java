package no.idata1002.group19.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import no.idata1002.group19.domain.entity.Budget;
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
public class BudgetController {
    
    @Autowired
    private BudgetRepository repository;


    @GetMapping(value = "/budgets")
    public Iterable<Budget> getBudgets() {
        return repository.findAll();
    }
}
