package com.example.test.config;

import com.example.test.exception.EmployerNotFoundException;
import com.example.test.exception.JobNotFound;
import com.example.test.exception.JobSeekerNotFoundException;
import com.example.test.exception.RoleNotFoundException;
import com.example.test.service.EmployerService;
import com.example.test.utility.ResponseUtility;
import lombok.AllArgsConstructor;


import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private ResponseUtility responseUtility;



    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ResponseUtility> handleNoRole(RoleNotFoundException r){
        responseUtility.setMessage(r.getMessage());
        return ResponseEntity
                .badRequest()
                .body(responseUtility);
    }

    @ExceptionHandler(EmployerNotFoundException.class)
    public ResponseEntity<ResponseUtility> handleNoEmployer(EmployerNotFoundException r){
        responseUtility.setMessage(r.getMessage());
        return ResponseEntity
                .badRequest()
                .body(responseUtility);
    }

    @ExceptionHandler(JobSeekerNotFoundException.class)
    public ResponseEntity<ResponseUtility> handleNoJobSeeker(JobSeekerNotFoundException r){
        responseUtility.setMessage(r.getMessage());
        return ResponseEntity
                .badRequest()
                .body(responseUtility);
    }

    @ExceptionHandler(JobNotFound.class)
    public ResponseEntity<ResponseUtility> handleNoJob(JobNotFound r){
        responseUtility.setMessage(r.getMessage());
        return ResponseEntity
                .badRequest()
                .body(responseUtility);
    }

}
