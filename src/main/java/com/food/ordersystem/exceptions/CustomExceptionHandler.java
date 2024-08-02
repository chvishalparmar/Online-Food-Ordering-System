package com.food.ordersystem.exceptions;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.food.ordersystem.dto.APIResponse;
import com.food.ordersystem.dto.ErrorDto;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        APIResponse<?> response = new APIResponse<>();
        response.setStatus("FAILED");
        List<ErrorDto> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    ErrorDto errorDTO = new ErrorDto(error.getField(), error.getDefaultMessage());
                    errors.add(errorDTO);
                });
        response.setStatus("FAILED");
        response.setErrors(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<APIResponse<?>> handleUserNotFoundException(UserNotFoundException e) {

       APIResponse<?> response = new APIResponse<>();
       response.setStatus("FAILED");
       response.setErrors(Collections.singletonList(new ErrorDto("Username", e.getMessage())));
       return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
