package org.example.exception.handler;

import org.example.exception.CustomRestException;
import org.example.exception.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllersExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = {CustomRestException.class})
    protected ResponseEntity<Object> handleCustomRestException(CustomRestException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getErrorResponse(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value
            = {HttpClientErrorException.class})
    protected ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorResponse(ex.getMessage(), "product_service"),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
