package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(TrackNotFoundException.class)
    public ResponseEntity<?> trackNotFoundException(final TrackNotFoundException te) {
        return new ResponseEntity<>(te.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrackAlreadyExistsException.class)
    public ResponseEntity<?> trackAlreadyExistsException(final TrackAlreadyExistsException te) {
        return new ResponseEntity<>(te.getMessage(), HttpStatus.OK);
    }

}
