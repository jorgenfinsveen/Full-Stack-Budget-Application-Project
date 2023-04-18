package no.idata1002.group19.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import no.idata1002.group19.domain.entity.User;

/**
 * Represents an interface for User Repository, that
 * extends to CrudRepository.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@RepositoryRestResource(exported = true)
public interface UserRepository extends CrudRepository<User, Long>{

    Optional<User> findByUsername(String username);

    @Query(value = "select u.bid from user u where u.username = ?1", nativeQuery = true)
    Optional<String> getBudgetByUsername(String username);
}
