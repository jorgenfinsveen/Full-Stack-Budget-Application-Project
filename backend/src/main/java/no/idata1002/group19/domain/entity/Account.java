package no.idata1002.group19.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * <h1>Account</h1>
 * 
 * The Account class represents a bank account entity with a 
 * unique identifier, user associated with it, and current balance.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@Entity(name = "account")
public class Account {
    
    /** The unique id of the account */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "aid")
    private long aid;

    /** The user of the account */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="\"user\"")
    private User user;

    /** The balance of the account */
    @Column(name = "account_balance", nullable = false)
    private double balance;

    /**
     * Empty constructor.
     */
    public Account() {}


    /**
     * Creates an instance of the Account.
     * 
     * @param user      The user of the Account
     * @param balance   The balance of the Account
     */
    public Account(User user, double balance) {
        super();
        this.user = user;
        this.balance = balance;
    }


    /**
     * Returns the id of the Account.
     * 
     * @return the id of the Account
     */
    public long getAid() {
        return aid;
    }

    /**
     * Sets the id of the Account.
     * 
     * @param aid the id of the Account
     */
    public void setAid(long aid) {
        this.aid = aid;
    }

    /**
     * Returns the user of the Account.
     * 
     * @return the user of the Account
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user of the Account.
     * 
     * @param user the user of the Account
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the balance of the Account.
     * 
     * @return the balance of the Account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the Account.
     * 
     * @param balance the balance of the Account.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
