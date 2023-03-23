package no.idata1002.group19.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import no.idata1002.group19.domain.entity.Expense;
import no.idata1002.group19.domain.repository.ExpenseRepository;

@RestController
public class ExpenseController {
    
    @Autowired
    private ExpenseRepository repository;


    @GetMapping(value = "/expenses")
    public Iterable<Expense> getExpenses() {
        return repository.findAll();
    }
}
