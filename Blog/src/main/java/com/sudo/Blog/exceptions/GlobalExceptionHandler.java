package com.sudo.Blog.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Controller
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex)
    {
        log.error("Caught Exception",ex);
        ApiErrorResponse error = ApiErrorResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())  //500 internal server error
            .message("An Unexpected Error Occurred")
            .build();
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgument(IllegalArgumentException ex)
    {
        ApiErrorResponse error = ApiErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value()) //400 bad gateway
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalStateException(IllegalArgumentException ex)
    {
        ApiErrorResponse error = ApiErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value()) //409 client error
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException ex)
    {
        ApiErrorResponse error = ApiErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value()) //401 unauthorized error
                .message("Incorrect Username or Password")
                .build();
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse>handleEntityNotFoundException(EntityNotFoundException ex)
    {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return new  ResponseEntity<>( errorResponse,HttpStatus.NOT_FOUND);
    }


}
