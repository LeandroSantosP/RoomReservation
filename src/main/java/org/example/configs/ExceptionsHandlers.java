package org.example.configs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandlers  extends ResponseEntityExceptionHandler {
    private record HttpErrorFormater(String type, HttpStatus status, String message) {
    }
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<HttpErrorFormater> handlerDefault(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HttpErrorFormater("default", HttpStatus.BAD_REQUEST, ex.getMessage()));
    }
}
