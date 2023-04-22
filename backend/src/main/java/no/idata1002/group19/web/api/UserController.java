package no.idata1002.group19.web.api;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import no.idata1002.group19.domain.entity.Budget;
import no.idata1002.group19.domain.entity.User;
import no.idata1002.group19.domain.entity.UserCredentials;
import no.idata1002.group19.domain.repository.BudgetRepository;
import no.idata1002.group19.domain.repository.UserRepository;

/**
 * Controller class for user.
 * Controls the endpoints for user.
 *
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BudgetRepository budgetRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    /**
     * Retrieves a list of all users from the UserService and returns them as an HTTP response.
     *
     * @return ResponseEntity<List<User>> - an HTTP response containing a list of all users
     */
    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return repository.findAll();
    }


    /**
     * Retrieves a user from userRepository and returns them as an HTTP response
     *
     * @param id of the user that you want to get
     * @return ResponseEntity.ok - and HTTP response containing the user.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        ResponseEntity<?> response;
        Optional<User> opt = repository.findById(id);

        if (opt.isPresent()) {
            response = ResponseEntity.ok(opt.get());
        } else {
            response = (ResponseEntity<?>) ResponseEntity.notFound();
        }
        return response;
    }


    /**
     * Adds a new user to the system using the UserService and returns an appropriate HTTP response.
     *
     * @param user - the User object representing the user to add.
     * @return ResponseEntity - an HTTP response indicating whether the user was added successfully.
     */
    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody UserCredentials credentials) {
        Budget budget = new Budget(  
            LocalDate.of(2023, 4, 18),
            LocalDate.of(2023, 8, 19),
            10000
        );

        budgetRepository.save(budget);

        User user = new User(
            credentials.getUsername(),
            bCryptPasswordEncoder.encode(credentials.getPassword()),
            "USER",
            budget
        );
        repository.save(user);

        return new ResponseEntity<>("User was added", HttpStatus.CREATED);
    }

    /**
     * Updates a user with the specified ID using the UserService and returns an appropriate HTTP response.
     *
     * @param id - the ID of the user to update.
     * @param user - the User object representing the updated user.
     * @return ResponseEntity - an HTTP response indicating whether the user was updated successfully.

     */
    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody UserCredentials credentials) {
        ResponseEntity<?> response;
        Optional<User> opt = repository.findById(id);
        Optional<Budget> budget = budgetRepository.findById(credentials.getBid());

        if (opt.isPresent()) {
            if (budget.isPresent()) {
                User tuple = opt.get();
                repository.delete(tuple);
                tuple.setUsername(credentials.getUsername());
                tuple.setPassword(credentials.getPassword());
                tuple.setRole(credentials.getRole());
                repository.save(tuple);
                response = new ResponseEntity<>("User was updated", HttpStatus.OK);
            } else {
                response = new ResponseEntity<>("Budget was not found.", HttpStatus.NOT_FOUND);
            }
        } else {
            response = new ResponseEntity<>("User was not found.", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Deletes a user with the specified ID using the UserService and returns an appropriate HTTP response.
     * @param id - the ID of the user to delete.
     * @return ResponseEntity - an HTTP response indicating whether the user was deleted successfully.
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        ResponseEntity<?> response;
        Optional<User> opt = repository.findById(id);

        if (opt.isPresent()) {
            repository.delete(opt.get());
            response = new ResponseEntity<>("User was removed", HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("User was not found", HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
