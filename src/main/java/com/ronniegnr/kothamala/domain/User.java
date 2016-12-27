package com.ronniegnr.kothamala.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class User {
    private long id;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String email;
    private boolean activated;
    private String langKey;
    private String activationKey;
    private String resetKey;
    private Timestamp resetDate;
    private String createdBy;
    private Timestamp createdDate;
    private String lastModifiedBy;
    private Timestamp lastModifiedDate;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "password_hash", nullable = true, length = 60)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "activated", nullable = false)
    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Basic
    @Column(name = "lang_key", nullable = true, length = 5)
    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    @Basic
    @Column(name = "activation_key", nullable = true, length = 20)
    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    @Basic
    @Column(name = "reset_key", nullable = true, length = 20)
    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    @Basic
    @Column(name = "reset_date", nullable = true)
    public Timestamp getResetDate() {
        return resetDate;
    }

    public void setResetDate(Timestamp resetDate) {
        this.resetDate = resetDate;
    }

    @Basic
    @Column(name = "created_by", nullable = false, length = 50)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "created_date", nullable = false)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "last_modified_by", nullable = true, length = 50)
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Basic
    @Column(name = "last_modified_date", nullable = true)
    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (activated != user.activated) return false;
        if (passwordHash != null ? !passwordHash.equals(user.passwordHash) : user.passwordHash != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (langKey != null ? !langKey.equals(user.langKey) : user.langKey != null) return false;
        if (activationKey != null ? !activationKey.equals(user.activationKey) : user.activationKey != null) return false;
        if (resetKey != null ? !resetKey.equals(user.resetKey) : user.resetKey != null) return false;
        if (resetDate != null ? !resetDate.equals(user.resetDate) : user.resetDate != null) return false;
        if (createdBy != null ? !createdBy.equals(user.createdBy) : user.createdBy != null) return false;
        if (createdDate != null ? !createdDate.equals(user.createdDate) : user.createdDate != null) return false;
        if (lastModifiedBy != null ? !lastModifiedBy.equals(user.lastModifiedBy) : user.lastModifiedBy != null) return false;
        if (lastModifiedDate != null ? !lastModifiedDate.equals(user.lastModifiedDate) : user.lastModifiedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (activated ? 1 : 0);
        result = 31 * result + (langKey != null ? langKey.hashCode() : 0);
        result = 31 * result + (activationKey != null ? activationKey.hashCode() : 0);
        result = 31 * result + (resetKey != null ? resetKey.hashCode() : 0);
        result = 31 * result + (resetDate != null ? resetDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
        result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
        return result;
    }
}
