package br.com.levi.apiteste.resources.exceptions;

import br.com.levi.apiteste.services.exceptions.DataIntegrityViolationException;
import br.com.levi.apiteste.services.exceptions.ObjectnotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHander {

    @ExceptionHandler(ObjectnotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectnotFoundException ex, HttpServletRequest request){
        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> objectNotFound(DataIntegrityViolationException ex, HttpServletRequest request){
        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}














