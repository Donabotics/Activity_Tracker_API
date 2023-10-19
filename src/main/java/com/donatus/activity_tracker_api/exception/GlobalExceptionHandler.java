package com.donatus.activity_tracker_api.exception;

import com.donatus.activity_tracker_api.entity.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleClientNotFoundException(final ClientNotFoundException exception){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .debugMessage("Client not in database")
                .dateTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
    }

    @ExceptionHandler(InvalidClientPasswordException.class)
    public ResponseEntity<ErrorDetails> handleInvalidClientPasswordException(final InvalidClientPasswordException exception){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(exception.getMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .debugMessage("Password incorrect!")
                .dateTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleTaskNotFoundException(final TaskNotFoundException exception){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .debugMessage("Check the Task id")
                .dateTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
    }

    @ExceptionHandler(DuplicateEmailAddressException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateEmailAddressException(final DuplicateEmailAddressException exception){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .debugMessage("Login or register with a new email")
                .dateTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
    }

}
