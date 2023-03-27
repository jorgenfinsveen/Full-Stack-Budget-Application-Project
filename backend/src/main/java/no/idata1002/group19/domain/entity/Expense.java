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

@Entity(name = "expense")
public class Expense {
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "eid")
    private long eid;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="budget")
    private Budget budget;

    @Column(name = "transaction_date")
    private LocalDateTime date;

    @Column(name = "transaction_value")
    private double value;

    @Column(name = "expense_name")
    private String name;

    @Column(name = "description")
    private String description;


    public Expense() {}


    public Expense(Budget budget, LocalDateTime date, double value, String name, String description) {
        super();
        this.budget = budget;
        this.date = date;
        this.value = value;
        this.name = name;
        this.description = description;
    }


    public long getEid() {
        return eid;
    }

    public void setEid(long eid) {
        this.eid = eid;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
