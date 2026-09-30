package com.assessment.datacollector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for any TechnicalFailureException
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TechnicalFailureException extends AbstractServiceException {

    /**
     * Constructor that allows to customize the error message
     *
     * @param message the error message to be sent as response to indicate the issue
     */
    public TechnicalFailureException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_CODE.TECHNICAL_FAILURE.toString(), message);
    }

}
