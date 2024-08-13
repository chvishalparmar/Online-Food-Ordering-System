package com.food.ordersystem.exceptions;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.food.ordersystem.dto.APIResponse;
import com.food.ordersystem.dto.ErrorDto;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@RestControllerAdvice
public class CustomExceptionHandler {

    public static final String FAILED = "Failed";

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex){
        ProblemDetail errorDetails = null;
        if(ex instanceof BadCredentialsException){
            errorDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetails.setProperty("access_denied_reason", "Authentication Failure");
        }

        if(ex instanceof AccessDeniedException){
            errorDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetails.setProperty("access_denied_reason", "Not Authorized !");
        }

        if(ex instanceof SignatureException){
            errorDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetails.setProperty("access_denied_reason", "JWT Signature Not Valid !");
        }

        if(ex instanceof ExpiredJwtException){
            errorDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetails.setProperty("access_denied_reason", "JWT Token already expired !");
        }
        return errorDetails;


    }
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
        response.setStatus(FAILED);
        response.setErrors(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<APIResponse<?>> handleUserNotFoundException(UserNotFoundException e) {

       APIResponse<?> response = new APIResponse<>();
       response.setStatus(FAILED);
       response.setErrors(Collections.singletonList(new ErrorDto("Username", e.getMessage())));
       return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DishNotFoundException.class)
    public ResponseEntity<APIResponse<?>> handleDishNotFoundException(DishNotFoundException e){

        APIResponse<?> response = new APIResponse<>();
        response.setStatus(FAILED);
        response.setErrors(Collections.singletonList(new ErrorDto("Dish", e.getMessage())));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InvalidEnumValueException.class)
    public ResponseEntity<APIResponse<?>> handleInvalidEnumValueException(InvalidEnumValueException e){
        APIResponse<?> response = new APIResponse<>();
        response.setStatus(FAILED);
        response.setErrors(Collections.singletonList(new ErrorDto("Cuisine", e.getMessage())));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BillExceptionHandler.class)
    public ResponseEntity<APIResponse<?>> handleBillExceptionHandlerException(BillExceptionHandler e){
        APIResponse<?> response = new APIResponse<>();
        response.setStatus(FAILED);
        response.setErrors(Collections.singletonList(new ErrorDto("Bill", e.getMessage())));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<APIResponse<?>> handleJwtException(JwtException e, WebRequest request){
        APIResponse<?> response = new APIResponse<>();
        response.setStatus(FAILED);
        response.setErrors(Collections.singletonList(new ErrorDto("Token", e.getMessage())));
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

    }

}
