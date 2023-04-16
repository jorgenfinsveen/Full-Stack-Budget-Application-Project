package no.idata1002.group19.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import no.idata1002.group19.domain.entity.Budget;
import no.idata1002.group19.domain.entity.User;

/**
 * Represents an interface for Budget Repository, that
 * extends to CrudRepository.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@RepositoryRestResource
public interface BudgetRepository extends CrudRepository<Budget, Long>{
    
    Optional<Budget> findByBid(@Param("bid") long bid);
    
    List<Budget> findByUser(@Param("user") User user);

    List<Budget> findByStartdate(@Param("startdate") LocalDateTime startdate);

    List<Budget> findByEnddate(@Param("enddate") LocalDateTime enddate);
}
