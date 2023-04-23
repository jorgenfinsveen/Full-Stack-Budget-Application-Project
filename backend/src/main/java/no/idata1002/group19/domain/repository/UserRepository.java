package no.idata1002.group19.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import no.idata1002.group19.domain.entity.User;

/**
 * A repository for User objects, providing CRUD operations 
 * and a custom query for finding a user by their
 * username and getting their associated budget ID.
 * 
 * @author Group19
 * @since 16.04.2023
 * @version 16.04.2023
 */
@RepositoryRestResource(exported = true)
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Finds a user by their username.
     * 
     * @param username The username of the user to find.
     * @return An Optional containing the User object if found, otherwise an empty
     *         Optional.
     */
    Optional<User> findByUsername(String username);

    /**
     * 
     * Retrieves the budget ID associated with the given username.
     * 
     * @param username The username of the user whose budget ID to retrieve.
     * @return An Optional containing the budget ID as a String if found, otherwise
     *         an empty Optional.
     */
    @Query(value = "select u.bid from user u where u.username = ?1", nativeQuery = true)
    Optional<String> getBudgetByUsername(String username);
}