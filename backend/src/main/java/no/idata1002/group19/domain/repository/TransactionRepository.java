package no.idata1002.group19.domain.repository;

import no.idata1002.group19.domain.entity.Transaction;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * A repository for transactions, which extends CrudRepository.
 * This repository provides methods for retrieving transactions from the
 * database, as well as updating existing transactions.
 * 
 * @author Group19
 * @since 16.04.2023
 * @version 23.04.2023
 */
@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    /**
     * Retrieves all expenses for a given budget, ordered by date in ascending
     * order.
     *
     * @param budgetId the ID of the budget to retrieve expenses for
     * @return a list of expenses for the given budget, ordered by date in ascending
     *         order
     */
    @Query(value = "select * from transaction t where t.value < 0 and t.budget_id = ?1 order by t.date asc", nativeQuery = true)
    List<Transaction> getExpensesByBudgetIdOrderByDateAsc(int budgetId);

    /**
     * Retrieves all incomes for a given budget, ordered by date in ascending order.
     *
     * @param budgetId the ID of the budget to retrieve incomes for
     * @return a list of incomes for the given budget, ordered by date in ascending
     *         order
     */
    @Query(value = "select * from transaction t where t.value > 0 and t.budget_id = ?1 order by t.date asc", nativeQuery = true)
    List<Transaction> getIncomesByBudgetIdOrderByDateAsc(int budgetId);

    /**
     * Retrieves all dates for transactions associated with a given budget, ordered
     * by date in ascending order.
     *
     * @param budgetId the ID of the budget to retrieve transaction dates for
     * @return a list of transaction dates for the given budget, ordered by date in
     *         ascending order
     */
    @Query(value = "select t.date from transaction t where t.budget_id = ?1 order by t.date asc", nativeQuery = true)
    List<String> getTransactionsDatesByBudgetIdOrderByDateAsc(long budgetId);

    /**
     * Updates an existing transaction with new information.
     *
     * @param tid         the ID of the transaction to update
     * @param date        the new date for the transaction
     * @param description the new description for the transaction
     * @param name        the new name for the transaction
     * @param value       the new value for the transaction
     */
    @Transactional
    @Modifying
    @Query(value = "update transaction t set t.date = ?2, t.description = ?3, t.tname = ?4, t.value = ?5 where t.tid = ?1", nativeQuery = true)
    void updateTransaction(long tid, LocalDate date, String description, String name, int value);
}
