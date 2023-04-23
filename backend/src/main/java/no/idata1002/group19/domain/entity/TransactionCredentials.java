package no.idata1002.group19.domain.entity;

/**
 * 
 * Represents the credentials required to create a transaction.
 * 
 * @author Group19
 * @since 16.04.2023
 * @version 23.04.2023
 */

public class TransactionCredentials {

    private String name;
    private int value;
    private String description;
    private String date;
    private long bid;

    /**
     * Gets the name of the transaction.
     * 
     * @return the name of the transaction
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the transaction.
     * 
     * @param name the name of the transaction
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of the transaction.
     * 
     * @return the value of the transaction
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the transaction.
     * 
     * @param value the value of the transaction
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets the description of the transaction.
     * 
     * @return the description of the transaction
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the transaction.
     * 
     * @param description the description of the transaction
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the date of the transaction.
     * 
     * @return the date of the transaction
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the transaction.
     * 
     * @param date the date of the transaction
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the budget ID of the transaction.
     * 
     * @return the budget ID of the transaction
     */
    public long getBid() {
        return bid;
    }

    /**
     * Sets the budget ID of the transaction.
     * 
     * @param bid the budget ID of the transaction
     */
    public void setBid(long bid) {
        this.bid = bid;
    }
}
