package no.idata1002.group19.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The User class represents a user entity with a 
 * unique identifier, and username, password,
 * and role.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@Entity(name = "\"user\"")
@Table(name = "\"user\"")
public class User {
    
    /** The id of the user */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false, updatable = false)
    private long uid;

    /** The username of the user */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /** The password for the user */
    @Column(name = "password", nullable = false)
    private String password;

    /** The role of the user */
    @Column(nullable = false)
    private String role;


    /**
     * Empty contructor.
     */
    public User() {}


    /**
     * Creates an instance of the User.
     * 
     * @param username  The username of the User
     * @param password  The password of the User
     * @param role      The role of the User
     */
    public User(String username, String password, String role) {
        super();
        this.username = username;
        this.password = password;
        this.role = role;
    }


    /**
     * Returns the id of the User.
     * 
     * @return the id of the User
     */
    public Long getUid() {
        return uid;
    }

    /**
     * Sets the id of the User.
     * 
     * @param uid the id of the User
     */
    public void setUid(long uid) {
        this.uid = uid;
    }

    /**
     * Returns the username of the User.
     * 
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the User.
     * 
     * @param username the username of the User
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password for the User.
     * 
     * @return the password for the User
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the User.
     * 
     * @param password the password for the User
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the role of the User.
     * 
     * @return the role of the User
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the User.
     * 
     * @param role the role of the User
     */
    public void setRole(String role) {
        this.role = role;
    }
}
