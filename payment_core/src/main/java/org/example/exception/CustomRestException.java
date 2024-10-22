package org.example.exception;

import org.example.exception.dto.ErrorResponse;

public class CustomRestException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public CustomRestException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
