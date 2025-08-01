package com.example.BundleTrigger.Exception;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.BundleTrigger.DTO.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> handleDuplicateConfig(DuplicateConfigException error){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(false,error.getMessage(),null)); 
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidDataAccess(InvalidDataAccessApiUsageException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, "Invalid Request: ID must not be null.", null));
        }


    private String getMeaningfulMessage(Exception ex) {
        if (ex instanceof IllegalArgumentException) {
                return "Invalid argument provided: " + ex.getMessage();
        } 
        else if (ex instanceof NullPointerException) {
                return "Unexpected null value encountered";
        } 
        else if (ex instanceof MissingServletRequestParameterException) {
                return "Required request parameter is missing";
        }
        
        return "An unexpected internal error occurred";
        }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleAllExceptions(Exception ex) {
                String errorMessage = getMeaningfulMessage(ex);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, errorMessage, null));
                
        }



    /*
     * 
     * Handle InternalServerError
     * Handle Conflict 
     */
    
    
}
