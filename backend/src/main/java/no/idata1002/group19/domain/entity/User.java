package no.idata1002.group19.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Represent user entity.
 *
 * @author Group19
 * @since 16.04.2023
 * @version 16.04.2023
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid")
    private Budget budget;

    /**
     * Default contractor for user.
     *
     * @param userName the name of the user.
     * @param pass     the password for the user.
     */
    public User(String username, String password, String role, Budget budget) {
        super();
        this.username = stringChecker(username, "username");
        this.password = stringChecker(password, "password");
        this.role = stringChecker(role, "role");
        this.budget = budget;
    }

    /**
     * Checks if the string is valid
     *
     * @param string  the string you want to check.
     * @param prefiks name of the string.
     * @return if the string is correct it returns the string
     */
    private String stringChecker(String string, String prefiks) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("The string " + "'" + prefiks + "'" + " cant be empty or null");
        }
        return string;
    }

    /**
     * Empty constructor that is needed for JPA
     */
    public User() {
    }

    /**
     * Returns the user id.
     * 
     * @return uid
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns user name
     * 
     * @return userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns password.
     * 
     * @return pass.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setts the user id
     * 
     * @param uid the id of the user that you want.
     */
    public void setId(Long uid) {
        this.id = uid;
    }

    /**
     * Setts the username.
     * 
     * @param userName the name of the user that you want.
     */
    public void setUsername(String username) {
        this.username = stringChecker(username, "username");
    }

    /**
     * Setts the password for the user.
     * 
     * @param pass the password of the user that you want.
     */
    public void setPassword(String password) {
        this.password = stringChecker(password, "pass");
    }

    /**
     * Return role
     * 
     * @return role
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Setts the role of the user
     * 
     * @param role the role of the user.
     */
    public void setRole(String role) {
        this.role = stringChecker(role, "role");
    }

    /**
     * Return budget
     * 
     * @return budget
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     * Setts budget
     * 
     * @param budget budget
     */
    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    /**
     * Checks of the user is valid.
     *
     * @return boolean statement. True if valid, false if not.
     */
    public boolean isValid() {
        boolean valid = true;

        if (username == null || username.isBlank()) {
            valid = false;
        } 
        if (password == null || password.isBlank()) {
            valid = false;
        }
        if (role == null || role.isBlank()) {
            valid = false;
        }
        if (budget == null || !budget.isValid()) {
            valid = false;
        }

        return valid;
    }
}
