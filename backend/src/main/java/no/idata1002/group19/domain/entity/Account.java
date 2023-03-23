package no.idata1002.group19.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "account")
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "aid")
    private long aid;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="\"user\"")
    private User user;

    @Column(name = "account_balance", nullable = false)
    private double balance;


    public Account() {}


    public Account(User user, double balance) {
        super();
        this.user = user;
        this.balance = balance;
    }


    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
