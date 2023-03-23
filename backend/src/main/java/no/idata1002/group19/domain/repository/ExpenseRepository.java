package no.idata1002.group19.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import no.idata1002.group19.domain.entity.Budget;
import no.idata1002.group19.domain.entity.Expense;

@RepositoryRestResource
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    
    Optional<Expense> findByEid(@Param("eid") long eid);
    
    List<Expense> findByBudget(@Param("budget") Budget budget);

    List<Expense> findByDate(@Param("date") LocalDateTime date);
}
