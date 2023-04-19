package no.idata1002.group19.domain.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * This class represent Transaction entity.
 *
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private long tid;

    @NotNull
    @Column(name = "tname", nullable = false)
    private String tname;

    @NotNull
    @Column(name = "value", nullable = false)
    private int value;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "budget_id", referencedColumnName = "bid")
    private Budget budget;

    /**
     * Default constructor for Transaction.
     *
     * @param tname the name of the transaction.
     * @param value the value of the transaction.
     * @param description description of the transaction.
     * @param date the date of the transaction.
     */
    public Transaction(String tname, int value, String description, LocalDate date, Budget budget) {
        super();
        this.tname = stringChecker(tname, "tname");
        this.value = numberChecker(value, "value");
        this.description = stringChecker(description, "description");
        this.date = date;
        this.budget = budget;
    }

    /**
     * Empty constructor that is needed for JPA
     */
    public Transaction() {}

    /**
     * Checks if the string is valid
     *
     * @param string the string you want to check.
     * @param prefiks name of the string.
     * @return if the string is correct it returns the string
     */
    private String stringChecker(String string, String prefiks) {
        if(string == null || string.trim().isEmpty()) {
            throw new IllegalArgumentException("The string " + "'" + prefiks + "'" + " cant be empty or null");
        }
        return string;
    }

    /**
     * Checks if the number is valid
     *
     * @param n the number to be checkt.
     * @param prefiks name of the number
     * @return if the number is correct, returns the number.
     */
    private int numberChecker(int n, String prefiks) {
        if(n < 0) {
            throw new IllegalArgumentException("The integer " + prefiks + " cant be below 0");
        }
        return n;
    }

    /**
     * Returns the id of transaction.
     * @return tid.
     */
    public long getTid() {
        return tid;
    }

    /**
     * Returns the name of the transaction.
     * @return tname.
     */
    public String getTname() {
        return tname;
    }

    /**
     * Returns the value of the transaction.
     * @return value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the description of the transaction.
     * @return transaction.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the date of the transaction.
     * @return date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Setts the id of the transaction.
     * @param tid the id you want for transaction.
     */
    public void setTid(long tid) {
        this.tid = tid;
    }

    /**
     * Setts the name fo the transaction.
     * @param tname the name you want for transaction.
     */
    public void setTname(String tname) {
        this.tname = tname;
    }

    /**
     * Setts the value of the transaction.
     * @param value the vale you want for transaction.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Setts the description of the transaction.
     * @param description the description you want for transaction.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setts the date of the transaction.
     * @param date the date you want for transaction.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    /**
     * Checks if the transaction is valid.
     * @return boolean statement. True if valid, false if not.
     */
    public boolean isValid() {
        return this.tname != null && !this.tname.trim().isEmpty() && this.value >= 0
                && this.description != null && this.date != null;
    }
}
