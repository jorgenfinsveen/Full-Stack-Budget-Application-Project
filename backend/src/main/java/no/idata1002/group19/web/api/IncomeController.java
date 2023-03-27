package no.idata1002.group19.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import no.idata1002.group19.domain.entity.Income;
import no.idata1002.group19.domain.repository.IncomeRepository;

@RestController
public class IncomeController {
    
        
    @Autowired
    private IncomeRepository repository;


    @GetMapping(value = "/incomes")
    public Iterable<Income> getIncomes() {
        return repository.findAll();
    }
}
