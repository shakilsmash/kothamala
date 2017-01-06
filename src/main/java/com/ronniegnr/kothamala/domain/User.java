package com.ronniegnr.kothamala.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ronniegnr.kothamala.domain.enumeration.UserStatus;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (status != user.status) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (activationKey != null ? !activationKey.equals(user.activationKey) : user.activationKey != null) return false;
        if (resetKey != null ? !resetKey.equals(user.resetKey) : user.resetKey != null) return false;
        if (resetDate != null ? !resetDate.equals(user.resetDate) : user.resetDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (activationKey != null ? activationKey.hashCode() : 0);
        result = 31 * result + (resetKey != null ? resetKey.hashCode() : 0);
        result = 31 * result + (resetDate != null ? resetDate.hashCode() : 0);

        return result;
    }

}
