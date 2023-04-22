package no.idata1002.group19.domain.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import no.idata1002.group19.domain.entity.Budget;

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
    
//    Optional<Budget> findByBid(@Param("bid") long bid);
//
//    List<Budget> findByUser(@Param("user") User user);
//
//    List<Budget> findByStartdate(@Param("startdate") LocalDateTime startdate);
//
//    List<Budget> findByEnddate(@Param("enddate") LocalDateTime enddate);
//
    @Transactional
    @Modifying
    @Query(value = "update budget b set b.start_date = ?2, b.end_date = ?3, b.boundary = ?4 where b.bid = ?1",  nativeQuery = true )
    void updateBudget(long bid, LocalDate startdate, LocalDate enddate, int boundary);
}
