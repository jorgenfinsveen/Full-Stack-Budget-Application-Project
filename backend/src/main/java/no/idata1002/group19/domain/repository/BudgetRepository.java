package no.idata1002.group19.domain.repository;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import no.idata1002.group19.domain.entity.Budget;

/**
 * A repository for the Budget entity and extends the CrudRepository.
 * It provides methods to update Budget's start date, end date, and boundary.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@RepositoryRestResource
public interface BudgetRepository extends CrudRepository<Budget, Long>{

    /**
     * Updates a Budget's start date, end date, and boundary based on its ID.
     * 
     * @param bid       the ID of the Budget to be updated
     * @param startdate the new start date for the Budget
     * @param enddate   the new end date for the Budget
     * @param boundary  the new boundary for the Budget
     */
    @Transactional
    @Modifying
    @Query(value = "update budget b set b.start_date = ?2, b.end_date = ?3, b.boundary = ?4 where b.bid = ?1",  nativeQuery = true )
    void updateBudget(long bid, LocalDate startdate, LocalDate enddate, int boundary);
}
