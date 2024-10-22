package org.example.exception.handler;

import org.example.exception.CustomNotFoundException;
import org.example.exception.NotEnoughMoneyException;
import org.example.exception.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllersExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = {CustomNotFoundException.class, NotEnoughMoneyException.class, IllegalArgumentException.class})
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorResponse(ex.getMessage(), "product_service"),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
