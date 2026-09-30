package com.assessment.datacollector.exception;

import org.springframework.http.HttpStatus;

/**
 * Abstract exception that defines the structure of the
 * exceptions. The actual implementation details
 * are defined in the concrete Exception class that inherits from
 * this abstract class.
 */
public abstract class AbstractServiceException extends RuntimeException {


    public AbstractServiceException(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public AbstractServiceException(Throwable cause, HttpStatus httpStatus, String code, String message) {
        super(cause);
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    private HttpStatus httpStatus;

    /**
     * Use to store an error or message code
     */
    private String code = null;

    private String message = null;

}
