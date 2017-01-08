package com.ronniegnr.kothamala.security;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception in thrown in case of a inactive user trying to authenticate.
 */
public class UserNotActivatedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserNotActivatedException(String message) {
        super(message);
    }

    public UserNotActivatedException(String message, Throwable t) {
        super(message, t);
    }

}
