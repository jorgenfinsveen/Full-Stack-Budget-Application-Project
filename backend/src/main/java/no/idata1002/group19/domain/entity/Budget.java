package no.idata1002.group19.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This class represent the budget entity.
 *
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid")
    private long bid;

    @NotNull
    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    @OneToMany
    @JoinTable(
            name = "budget_transaction",
            joinColumns = @JoinColumn(name = "budget_id"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id")
    )
    private Set<Transaction> transactions;

    private static final Logger LOGGER = Logger.getLogger(Budget.class.getName());

    /**
     * Default constructor for budget
     * @param startDate start date of the budget. Format (yyyy-MM-dd)
     * @param endDate end date of the budget. Format (yyyy-MM-dd)
     */
    public Budget(LocalDate startDate, LocalDate endDate) {
        try {
            this.startDate = startDate;
            this.endDate = endDate;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(illegalArgumentException.getMessage());
        }
    }

    /**
     * Empty constructor that is needed for JPA
     */
    public Budget() {

    }

    /**
     * Returns the id of budget.
     * @return bid.
     */
    public long getBid() {
        return bid;
    }

    /**
     * Returns the start date of the budget
     * @return startDate.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the budget.
     * @return endDate.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Setts the id of budget
     * @param bid the bid that you want.
     */
    public void setBid(long bid) {
        this.bid = bid;
    }

    /**
     * Setts the start date of the budget.
     * @param startDate the start date you want. Format (yyyy-MM-dd).
     */
    public void setStartDate(LocalDate startDate) {
        try {
            this.startDate = startDate;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(illegalArgumentException.getMessage());
        }
    }

    /**
     * Setts the end date of the budget.
     * @param endDate the end date you want. Format (yyyy-MM-dd).
     */
    public void setEndDate(LocalDate endDate) {
        try {
            this.endDate = endDate;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(illegalArgumentException.getMessage());
        }
    }

    /**
     * Returns transactions
     * @return transactions
     */
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Setts transactions
     * @param transactions Set of transactions
     */
    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public boolean isValid() {
        return !(this.startDate == null) && !(this.endDate == null);
    }
}
