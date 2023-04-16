package no.idata1002.group19.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import no.idata1002.group19.domain.entity.Expense;
import no.idata1002.group19.domain.repository.ExpenseRepository;

/**
 * Represents an Expense Controller class that is a RESTful
 * controller that handles the HTTP requests.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@RestController
public class ExpenseController {
    
    @Autowired
    private ExpenseRepository repository;


    @GetMapping(value = "/expenses")
    public Iterable<Expense> getExpenses() {
        return repository.findAll();
    }
}
