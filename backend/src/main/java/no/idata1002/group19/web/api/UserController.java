package no.idata1002.group19.web.api;

import no.idata1002.group19.domain.entity.User;
import no.idata1002.group19.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for user.
 * Controls the endpoints for user.
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves a list of all users from the UserService and returns them as an HTTP response.
     *
     * @return ResponseEntity<List<User>> - an HTTP response containing a list of all users
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok((List<User>) this.userService.getAll());
    }

    /**
     * Retrieves a user from userRepository and returns them as an HTTP response
     *
     * @param id of the user that you want to get
     * @return ResponseEntity.ok - and HTTP response containing the user.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(this.userService.findById(id));
    }

    /**
     * Adds a new user to the system using the UserService and returns an appropriate HTTP response.
     *
     * @param user - the User object representing the user to add.
     * @return ResponseEntity - an HTTP response indicating whether the user was added successfully.
     */
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if(!this.userService.add(user)) {
            return new ResponseEntity<>("User was not added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("User was added", HttpStatus.CREATED);
    }

    /**
     * Updates a user with the specified ID using the UserService and returns an appropriate HTTP response.
     *
     * @param id - the ID of the user to update.
     * @param user - the User object representing the updated user.
     * @return ResponseEntity - an HTTP response indicating whether the user was updated successfully.

     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody User user) {
        User oldUSer = this.userService.findById(id);
        if (oldUSer == null) {
            return new ResponseEntity<>("didn't find user", HttpStatus.NOT_FOUND);
        }
        this.userService.update(id, user);
        if (this.userService.findById(id) == null) {
            return new ResponseEntity<>("User didn't update", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Product was updated", HttpStatus.OK);
    }

    /**
     * Deletes a user with the specified ID using the UserService and returns an appropriate HTTP response.
     * @param id - the ID of the user to delete.
     * @return ResponseEntity - an HTTP response indicating whether the user was deleted successfully.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        if (!this.userService.delete(id)) {
            return new ResponseEntity<>("User was not removed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("User was removed", HttpStatus.OK);
    }
}
