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
 * The Income class represents income entity with a 
 * unique identifier, Budget associated with it, date, value,
 * name and description.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@Entity(name = "income")
public class Income {
    
    /** The id of the income */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iid")
    private long iid;

    /** The budget of the income */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="budget")
    private Budget budget;

    /** The date of the income */
    @Column(name = "transaction_date")
    private LocalDateTime date;

    /** The value of the income */
    @Column(name = "transaction_value")
    private double value;

    /** The name of the income */
    @Column(name = "income_name")
    private String name;

    /** The description of the income */
    @Column(name = "description")
    private String description;


    /**
     * Empty constructor.
     */
    public Income() {}


    /**
     * Creates an instance of the Income.
     * 
     * @param budget        The budget of the income
     * @param date          The date of the income
     * @param value         The value of the income
     * @param name          The name of the income
     * @param description   The description of the income
     */
    public Income(Budget budget, LocalDateTime date, double value, String name, String description) {
        super();
        this.budget = budget;
        this.date = date;
        this.value = value;
        this.name = name;
        this.description = description;
    }


    /**
     * Retuns the id of the income.
     * 
     * @return the id of the income
     */
    public long getIid() {
        return iid;
    }

    /**
     * Sets the id of the income.
     * 
     * @param iid the id of the income
     */
    public void setIid(long iid) {
        this.iid = iid;
    }

    /**
     * Returns the budget of the income.
     * 
     * @return the budget of the income
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     * Sets the budget of the income.
     * 
     * @param budget the budget of the income
     */
    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    /**
     * Returns the date of the income.
     * 
     * @return the date of the income
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date of the income.
     * 
     * @param date the date of the income
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Returns the value of the income.
     * 
     * @return the value of the income
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the value of the income.
     * 
     * @param value the value of the income
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Returns the name of the income.
     * 
     * @return the name of the income
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the income.
     * 
     * @param name the name of the income
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the income.
     * 
     * @return the description of the income
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the income.
     * 
     * @param description the description of the income
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
