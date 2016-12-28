package com.ronniegnr.kothamala.service.dto;

import com.ronniegnr.kothamala.domain.User;
import com.ronniegnr.kothamala.domain.enumeration.UserStatus;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

/**
 * A DTO representing a user, with his autorities.
 */
public class UserDTO {

    private String email;
    private String firstName;
    private String lastName;
    private UserStatus status;

    public UserDTO() {

    }

    public UserDTO(User user) {
        this(user.getEmail(), user.getFirstName(), user.getLastName(), user.getStatus());
    }

    public UserDTO(String email, String firstName, String lastName, UserStatus status) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    @Email
    @Size(min = 5, max = 100)
    public String getEmail() {
        return email;
    }

    @Size(max = 50)
    public String getFirstName() {
        return firstName;
    }

    @Size(max = 50)
    public String getLastName() {
        return lastName;
    }

    @Size(max = 20)
    public UserStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
