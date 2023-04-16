package no.idata1002.group19.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import no.idata1002.group19.domain.entity.Budget;
import no.idata1002.group19.domain.entity.Income;

/**
 * Represents an interface for Income Repository, that
 * extends to CrudRepository.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@RepositoryRestResource
public interface IncomeRepository extends CrudRepository<Income, Long> {
    
    Optional<Income> findByIid(@Param("iid") long iid);
    
    List<Income> findByBudget(@Param("budget") Budget budget);

    List<Income> findByDate(@Param("date") LocalDateTime date);
}
