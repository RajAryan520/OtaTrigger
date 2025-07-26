package com.example.BundleTrigger.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.BundleTrigger.DTO.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> handleDuplicateConfig(DuplicateConfigException error){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(false,error.getMessage(),null)); 
    } 
    
    
}
