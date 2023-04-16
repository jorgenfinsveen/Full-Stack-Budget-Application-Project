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
 * The Expense class represents expenses entity with a 
 * unique identifier, budget associated with it, date,
 * value, name and a description.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@Entity(name = "expense")
public class Expense {
     
    /** The id of the expense */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "eid")
    private long eid;

    /** The budget of the expence */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="budget")
    private Budget budget;

    /** The date of the expense */
    @Column(name = "transaction_date")
    private LocalDateTime date;

    /** The value of the expense */
    @Column(name = "transaction_value")
    private double value;

    /** The name of the expense */
    @Column(name = "expense_name")
    private String name;

    /** The description of the expense */
    @Column(name = "description")
    private String description;


    /**
     * Empty constructor.
     */
    public Expense() {}


    /**
     * Creates an instance of the expense.
     * 
     * @param budget        The budget of the expense
     * @param date          The date of the expense
     * @param value         The value of the expense
     * @param name          The name of the expense
     * @param description   The description of the expense
     */
    public Expense(Budget budget, LocalDateTime date, double value, String name, String description) {
        super();
        this.budget = budget;
        this.date = date;
        this.value = value;
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the id of the expense.
     * 
     * @return the id of the expense
     */
    public long getEid() {
        return eid;
    }

    /**
     * Sets the id of the expense.
     * 
     * @param eid the id of the expense
     */
    public void setEid(long eid) {
        this.eid = eid;
    }

    /**
     * Returns the budget of the expense.
     * 
     * @return the budget of the expense
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     * Sets the budget of the expense.
     * 
     * @param budget the budget of the expense
     */
    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    /**
     * Returns the date of the expense.
     * 
     * @return the date of the expense
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date of the expense.
     * 
     * @param date the date of the expense
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Returns the value of the expense.
     * 
     * @return the value of the expense
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the value of the expense.
     * 
     * @param value the value of the expense
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Returns the name of the expense.
     * 
     * @return the name of the expense
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the expense.
     * 
     * @param name the name of the expense
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the expense.
     * 
     * @return the desription of the expense
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the expense.
     * 
     * @param description the description of the expense
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
