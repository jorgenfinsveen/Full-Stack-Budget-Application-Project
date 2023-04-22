package no.idata1002.group19.domain.repository;

import no.idata1002.group19.domain.entity.Transaction;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Query(value = "select * from transaction t where t.value < 0 and t.budget_id = ?1 order by t.date asc", nativeQuery = true)
    List<Transaction> getExpensesByBudgetIdOrderByDateAsc(int budgetId);

    @Query(value = "select * from transaction t where t.value > 0 and t.budget_id = ?1 order by t.date asc", nativeQuery = true)
    List<Transaction> getIncomesByBudgetIdOrderByDateAsc(int budgetId);

    @Query(value = "select t.date from transaction t where t.budget_id = ?1 order by t.date asc", nativeQuery = true)
    List<String> getTransactionsDatesByBudgetIdOrderByDateAsc(long budgetId);

    @Transactional
    @Modifying
    @Query(value = "update transaction t set t.date = ?2, t.description = ?3, t.tname = ?4, t.value = ?5 where t.tid = ?1",  nativeQuery = true )
    void updateTransaction(long tid, LocalDate date, String description, String name, int value);
}
