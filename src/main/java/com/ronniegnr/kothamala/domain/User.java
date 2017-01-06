package com.ronniegnr.kothamala.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ronniegnr.kothamala.domain.enumeration.UserStatus;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1;

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private UserStatus status;
    private String activationKey;
    private String resetKey;
    private Timestamp resetDate;

    private Set<Authority> authorities = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", nullable = false, length = 60)
    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
    }

    @NotNull
    @Size(max = 50)
    @Column(name = "first_name", nullable = false, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Size(max = 50)
    @Column(name = "last_name", nullable = false, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Email
    @Size(max = 100)
    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @Size(max = 20)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    @Column(name = "reset_date")
    public Timestamp getResetDate() {
        return resetDate;
    }

    public void setResetDate(Timestamp resetDate) {
        this.resetDate = resetDate;
    }

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
    )
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
