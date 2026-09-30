package com.assessment.datacollector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for ServiceNotAvailableException
 */
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceNotAvailableException extends AbstractServiceException {

    /**
     * Constructed when an exception is a Service that is not available
     *
     * @param message to indicate to the caller when service is not available
     */
    public ServiceNotAvailableException(String message) {
        super(HttpStatus.SERVICE_UNAVAILABLE, ERROR_CODE.NOT_AVAILABLE.toString(), message);
    }


}
