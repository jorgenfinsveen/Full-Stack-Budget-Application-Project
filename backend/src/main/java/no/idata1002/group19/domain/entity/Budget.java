package no.idata1002.group19.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * The Budget class represents a personal budget entity with a 
 * unique identifier, user associated with it, dates, limits and balance.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@Entity(name = "budget")
public class Budget {
    
    /** The id of the budget */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bid")
    private long bid;

    /** The user of the budget */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="\"user\"")
    private User user;

    /** The startdate of the budget */
    @Column(name = "start_date")
    private LocalDateTime startdate;

    /** The enddate of the budget */
    @Column(name = "end_date")
    private LocalDateTime enddate;

    /** The limit of the budget */
    @Column(name = "budget_limit")
    private double limit;

    /** The balance of the budget */
    @Column(name = "budget_balance")
    private double balance;


    /**
     * Empty constructor.
     */
    public Budget() {}


    /**
     * Creates an instance of Budget.
     * 
     * @param user      The user of the budget
     * @param startdate The startdate of the budget
     * @param enddate   The enddate of the budget
     * @param limit     The limit of the budget
     * @param balance   The balance of the budget
     */
    public Budget(User user, LocalDateTime startdate, LocalDateTime enddate, double limit, double balance) {
        super();
        this.user = user;
        this.startdate = startdate;
        this.enddate = enddate;
        this.limit = limit;
        this.balance = balance;
    }

    /**
     * The id of the budget.
     * 
     * @return the id of the budget
     */
    public long getBid() {
        return bid;
    }


    /**
     * Sets the id of the budget.
     * 
     * @param bid the id of the budget
     */
    public void setBid(long bid) {
        this.bid = bid;
    }


    /**
     * The user of the budget.
     * 
     * @return the user of the budget
     */
    public User getUser() {
        return user;
    }


    /**
     * Sets the user of the budget.
     * 
     * @param user the user of the budget
     */
    public void setUser(User user) {
        this.user = user;
    }


    /**
     * Returns the start date of the budget.
     * 
     * @return the start date of the budget
     */
    public LocalDateTime getStartdate() {
        return startdate;
    }


    /**
     * Sets the start date of the budget.
     * 
     * @param startdate the start date of the budget
     */
    public void setStartdate(LocalDateTime startdate) {
        this.startdate = startdate;
    }


    /**
     * Returns the end date of the budget.
     * 
     * @return the end date of the budget
     */
    public LocalDateTime getEnddate() {
        return enddate;
    }


    /**
     * Sets the enddate if the budget.
     * 
     * @param enddate the enddate of the budget
     */
    public void setEnddate(LocalDateTime enddate) {
        this.enddate = enddate;
    }


    /**
     * Returns the limit of the budget.
     * 
     * @return the limit of the budget
     */
    public double getLimit() {
        return limit;
    }


    /**
     * Sets the limit of the budget.
     * 
     * @param limit the limit of the budget
     */
    public void setLimit(double limit) {
        this.limit = limit;
    }


    /**
     * Returns the balance of the budget.
     * 
     * @return the balance of the budget
     */
    public double getBalance() {
        return balance;
    }


    /**
     * Sets the balance of the budget.
     * 
     * @param balance the balance of the budget
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
