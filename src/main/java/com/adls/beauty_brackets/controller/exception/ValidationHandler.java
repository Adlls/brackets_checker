package com.adls.beauty_brackets.controller.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.adls.beauty_brackets.model.ErrorRs;

@ControllerAdvice
public class ValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRs> handleException(MethodArgumentNotValidException exception) {
        final List<String> errorList = exception.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        final ErrorRs errorRs = ErrorRs.builder().message(String.join(", ", errorList)).build();
        return new ResponseEntity<ErrorRs>(errorRs, HttpStatus.BAD_REQUEST);
    }
}