package com.ronniegnr.kothamala.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private HeaderUtil() {
    }

    public static HttpHeaders createEntityCreationAlert(String entityname, Object identifier) {
        return createAlert("A new " + entityname + " is created with identifier " + identifier, identifier);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityname, Object identifier) {
        return createAlert("A " + entityname + " is  with identifier " + identifier, identifier);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityname, Object identifier) {
        return createAlert("A " + entityname + " is deleted with identifier " + identifier, identifier);
    }

    private static HttpHeaders createAlert(String message, Object identifier) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-kothamala-alert", message);
        headers.add("X-kothamala-params", identifier.toString());
        return headers;
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String message) {
        log.error("{} entity creation failed : {}", entityName, message);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-kothamala-error-key", errorKey);
        headers.add("X-kothamala-error", message);
        headers.add("X-kothamala-params", entityName);
        return headers;
    }

}
