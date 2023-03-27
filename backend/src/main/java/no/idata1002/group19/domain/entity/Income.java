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

@Entity(name = "income")
public class Income {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iid")
    private long iid;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="budget")
    private Budget budget;

    @Column(name = "transaction_date")
    private LocalDateTime date;

    @Column(name = "transaction_value")
    private double value;

    @Column(name = "income_name")
    private String name;

    @Column(name = "description")
    private String description;


    public Income() {}


    public Income(Budget budget, LocalDateTime date, double value, String name, String description) {
        super();
        this.budget = budget;
        this.date = date;
        this.value = value;
        this.name = name;
        this.description = description;
    }


    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
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
