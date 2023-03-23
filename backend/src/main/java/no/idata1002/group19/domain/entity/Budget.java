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

@Entity(name = "budget")
public class Budget {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bid")
    private long bid;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="\"user\"")
    private User user;

    @Column(name = "start_date")
    private LocalDateTime startdate;

    @Column(name = "end_date")
    private LocalDateTime enddate;

    @Column(name = "budget_limit")
    private double limit;

    @Column(name = "budget_balance")
    private double balance;


    public Budget() {}


    public Budget(User user, LocalDateTime startdate, LocalDateTime enddate, double limit, double balance) {
        super();
        this.user = user;
        this.startdate = startdate;
        this.enddate = enddate;
        this.limit = limit;
        this.balance = balance;
    }


    public long getBid() {
        return bid;
    }


    public void setBid(long bid) {
        this.bid = bid;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public LocalDateTime getStartdate() {
        return startdate;
    }


    public void setStartdate(LocalDateTime startdate) {
        this.startdate = startdate;
    }


    public LocalDateTime getEnddate() {
        return enddate;
    }


    public void setEnddate(LocalDateTime enddate) {
        this.enddate = enddate;
    }


    public double getLimit() {
        return limit;
    }


    public void setLimit(double limit) {
        this.limit = limit;
    }


    public double getBalance() {
        return balance;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }
}
