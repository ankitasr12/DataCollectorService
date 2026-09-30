package com.assessment.datacollector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for ResourceNotFound
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends AbstractServiceException {

    /**
     * Constructed when an exception is a Service that is not found
     *
     * @param message to indicate to the caller when service is not found
     */
    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, ERROR_CODE.NOT_FOUND.toString(), message);
    }

}
