package no.idata1002.group19.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import no.idata1002.group19.domain.entity.Budget;
import no.idata1002.group19.domain.entity.User;

@RepositoryRestResource
public interface BudgetRepository extends CrudRepository<Budget, Long>{
    
    Optional<Budget> findByBid(@Param("bid") long bid);
    
    List<Budget> findByUser(@Param("user") User user);

    List<Budget> findByStartdate(@Param("startdate") LocalDateTime startdate);

    List<Budget> findByEnddate(@Param("enddate") LocalDateTime enddate);
}
