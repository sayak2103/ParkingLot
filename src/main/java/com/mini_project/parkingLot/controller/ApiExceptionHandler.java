package com.mini_project.parkingLot.controller;

import com.mini_project.parkingLot.dto.ApiError;
import com.mini_project.parkingLot.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ApiError> resourceNotFound(ResourceNotFoundException exp, HttpServletRequest req){
        return error(HttpStatus.NOT_FOUND,"resource Not found Exception", exp.getMessage(), req);
    }

    private ResponseEntity<ApiError> error(HttpStatus httpStatus,String exp,  String msg, HttpServletRequest req){
        return ResponseEntity.status(httpStatus)
                .body(
                        new ApiError(
                                Instant.now(),
                                httpStatus.value(),
                                exp,
                                msg,
                                "/"
                        )
                );
    }

}
