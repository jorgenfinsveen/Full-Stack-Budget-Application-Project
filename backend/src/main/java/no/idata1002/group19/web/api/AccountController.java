package no.idata1002.group19.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import no.idata1002.group19.domain.entity.Account;
import no.idata1002.group19.domain.repository.AccountRepository;

/**
 * Represents an Account Controller class that is a RESTful
 * controller that handles the HTTP requests.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@RestController
public class AccountController {
    
    @Autowired
    private AccountRepository repository;


    @GetMapping(value = "/accounts")
    public Iterable<Account> getAccounts() {
        return repository.findAll();
    }
}
