package com.atbs.config;

import com.atbs.exception.*;
import com.atbs.utility.ResponseUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionController {

    private ResponseUtility responseUtility;

    @ExceptionHandler(UnAuthorizedAccessException.class)
    public ResponseEntity<Object> handleUnAuthorizesException(UnAuthorizedAccessException u){
        log.warn("Unauthorized access attempt:"+u.getMessage());
        responseUtility.setMessage(u.getMessage());
        return ResponseEntity.badRequest().body(responseUtility);
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException u){
        log.warn("User already exists: "+u.getMessage());
        responseUtility.setMessage(u.getMessage());
        return ResponseEntity.badRequest().body(responseUtility);
    }

    @ExceptionHandler(CancellationNotFoundException.class)
    public ResponseEntity<Object> handleCancellationException(CancellationNotFoundException c){
        log.warn("Cancellation not found:"+c.getMessage());
        responseUtility.setMessage(c.getMessage());
        return ResponseEntity.badRequest().body(responseUtility);
    }

    @ExceptionHandler(SeatNotAvailableExcption.class)
    public ResponseEntity<Object> handleSeatNotFoundException(SeatNotAvailableExcption s){
        responseUtility.setMessage(s.getMessage());
        return ResponseEntity.badRequest().body(responseUtility);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<Object>  handleFlightNotFoundException(FlightNotFoundException f){
        responseUtility.setMessage(f.getMessage());
        return ResponseEntity.badRequest().body(responseUtility);
    }
    @ExceptionHandler(FlightOwnerNotFoundException.class)
    public ResponseEntity<Object> handleFlightOwnerNotFound(FlightOwnerNotFoundException e) {
        responseUtility.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseUtility);
    }

    @ExceptionHandler(PassengerNotFoundException.class)
    public ResponseEntity<Object> handlePassengerNotFound(PassengerNotFoundException e) {
        responseUtility.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseUtility);
    }

    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<Object> handleRouteNotFound(RouteNotFoundException e) {
        responseUtility.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseUtility);
    }

    @ExceptionHandler(ScheduleNotFound.class)
    public ResponseEntity<Object> handleScheduleNotFound(ScheduleNotFound e) {
        responseUtility.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseUtility);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>  handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult=e.getBindingResult();
        List<FieldError> fieldErrors=bindingResult.getFieldErrors();
        Map<String,String> map=new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(map);
    }
}
