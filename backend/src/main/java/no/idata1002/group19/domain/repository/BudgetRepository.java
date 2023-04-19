package no.idata1002.group19.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

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
//    @Modifying
//    @Query("update budget b set b.bid = ?1, b.startDate = ?2, b.endDate = ?3, b.boundary = ?4 where b.bid = ?1")
//   void updateBudget(long bid, LocalDate startdate, LocalDate enddate, int boundary);
}
