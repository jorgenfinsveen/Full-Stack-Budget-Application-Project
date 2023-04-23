package no.idata1002.group19.domain.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Database entitytype representation of a budget.
 * 
 * <p>
 * Each user has a budget assigned to themselves,
 * and the budget holds data regarding the period
 * which the budget is supposed to be active, as well
 * as a upper boundary for how much the user wants to
 * spend. A budget has a one-to-many relationship with
 * the transaction entity-type.
 *
 * @author Group 19
 * @since 16.04.2023
 * @version 17.04.2023
 */
@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bid")
    private long bid;

    @NotNull
    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    @NotNull
    @Column(name = "boundary")
    private int boundary;

    /**
     * Default constructor for budget
     * 
     * @param startDate start date of the budget. Format (yyyy-MM-dd)
     * @param endDate   end date of the budget. Format (yyyy-MM-dd)
     * @param boundary  the upper bound of the budget spendings.
     */
    public Budget(LocalDate startDate, LocalDate endDate, int boundary) {
        super();
        this.startDate = startDate;
        this.endDate = endDate;
        this.boundary = boundary;
    }

    /**
     * Empty constructor that is needed for JPA
     */
    public Budget() {
    }

    /**
     * Returns the id of budget.
     * 
     * @return bid.
     */
    public long getBid() {
        return bid;
    }

    /**
     * Returns the start date of the budget
     * 
     * @return startDate.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the budget.
     * 
     * @return endDate.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the id of budget
     * 
     * @param bid the bid that you want.
     */
    public void setBid(long bid) {
        this.bid = bid;
    }

    /**
     * Sets the start date of the budget.
     * 
     * @param startDate the start date you want. Format (yyyy-MM-dd).
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the end date of the budget.
     * 
     * @param endDate the end date you want. Format (yyyy-MM-dd).
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns the boundary of the budget.
     * 
     * @return boundary.
     */
    public int getBoundary() {
        return this.boundary;
    }

    /**
     * Sets boundary of the budget.
     * 
     * @param setBoundary the end date you want. Format (yyyy-MM-dd).
     */
    public void setBoundary(int boundary) {
        this.boundary = boundary;
    }

    /**
     * Checks whether the budget has a valid start and end date.
     * A budget is considered valid if both start and end dates are not null.
     * 
     * @return true if the budget is valid, false otherwise.
     */
    public boolean isValid() {
        return (this.startDate != null) && (this.endDate != null);
    }
}
