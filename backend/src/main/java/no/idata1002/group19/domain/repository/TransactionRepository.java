package no.idata1002.group19.domain.repository;

import no.idata1002.group19.domain.entity.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Query(value = "select * from transaction t where t.value < 0 and t.budget_id = ?1", nativeQuery = true)
    List<Transaction> getExpensesByBudgetId(int budgetId);

    @Query(value = "select * from transaction t where t.value > 0 and t.budget_id = ?1", nativeQuery = true)
    List<Transaction> getIncomesByBudgetId(int budgetId);
}
