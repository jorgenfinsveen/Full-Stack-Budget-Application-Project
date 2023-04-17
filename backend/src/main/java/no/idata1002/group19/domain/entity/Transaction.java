package no.idata1002.group19.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.logging.Logger;

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
    @GeneratedValue
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

    @ManyToOne
    @JoinColumn(name = "budget_bid")
    private Budget budget;

    private static final Logger LOGGER = Logger.getLogger(Transaction.class.getName());
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_WARNING = "Caught Illegal Argument Exception: ";

    public Transaction(String tname, int value, String description, LocalDate date) {
        try {
            this.tname = stringChecker(tname, "tname");
            this.value = numberChecker(value, "value");
            this.description = stringChecker(description, "description");
            this.date = date;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Empty constructor that is needed for JPA
     */
    public Transaction() {

    }

    /**
     * Checks if the string is valid
     *
     * @param string the string you want to check.
     * @param prefiks name of the string.
     * @return if the string is correct it returns the string
     */
    private String stringChecker(String string, String prefiks) {
        if(string.isEmpty() || string == null) {
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

    /**
     * Checks if the transaction is valid.
     * @return boolean statement. True if valid, false if not.
     */
    public boolean isValid() {
        return !"".equals(this.tname) && this.value >= 0 && !"".equals(this.description);
    }
}
