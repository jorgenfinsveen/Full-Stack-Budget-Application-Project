package no.idata1002.group19.service;

import no.idata1002.group19.domain.entity.Budget;
import no.idata1002.group19.domain.entity.User;
import no.idata1002.group19.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class represent the service class for user entity
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Returns all users
     * @return users
     */
    public Iterable<User> getAll() {
        return this.userRepository.findAll();
    }

    /**
     * Return user from a given id
     * @param id id of the user you want to find
     * @return user
     */
    public User findById(long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    /**
     * Adds a user to the repository
     * @param user the user you want to add
     * @return boolean statement. True if added false if not.
     */
    public boolean add(User user) {
        boolean added = false;
        if (canBeAdded(user)) {
            this.userRepository.save(user);
            added = true;
        }
        return added;
    }

    /**
     * Checks if the user can be added to the repository.
     * @param user the user you want to check.
     * @return boolean statement. True if it can be added, false if not
     */
    private boolean canBeAdded(User user) {
        return user !=null && user.isValid();
    }

    /**
     * Removes a user from the repository.
     * @param id of the user you want to remove
     * @return boolean statement. True if user was removed, false if not.
     */
    public boolean delete(long id) {
        boolean deleted = false;
        if(findById(id) != null) {
            this.userRepository.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

    /**
     * Update an existing user.
     * @param id of the user that you want to update
     * @param user the user you want the old user to be updated to.
     */
    public void update(long id, User user) {
        User existingUser = findById(id);
        String errorMessage = null;
        if (existingUser == null) {
            errorMessage = "No budget exists with the id " + id;
        }
        if (user == null || !user.isValid()) {
            errorMessage = "Wrong data in request body";
        }
        else if(user.getUid() != id) {
            errorMessage = "The ID of the user in the URL does not match anny ID in the JSON data";
        }
        if (errorMessage == null) {
            this.userRepository.save(user);
        }
    }
}
