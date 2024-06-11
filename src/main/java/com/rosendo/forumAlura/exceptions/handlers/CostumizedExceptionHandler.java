package com.rosendo.forumAlura.exceptions.handlers;

import com.rosendo.forumAlura.exceptions.ExceptionResponse;
import com.rosendo.forumAlura.exceptions.InvalidJwtAuthenticationException;
import com.rosendo.forumAlura.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CostumizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(
            Exception exception,
            WebRequest webRequest
    ){
        ExceptionResponse exceptionsResponse = new ExceptionResponse(
                exception.getMessage(),
                new Date(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(exceptionsResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationExceptions(
            Exception exception, WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getMessage(),
                new Date(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

}
