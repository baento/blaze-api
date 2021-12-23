package fr.blaze.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(APIError apiException) {
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new APIError(HttpStatus.BAD_REQUEST, "Malformed JSON request", ex));
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleNotAuthenticated(AuthenticationException ex) {
        APIError apiException = new APIError(HttpStatus.UNAUTHORIZED);
        apiException.setMessage(ex.getMessage());
        return buildResponseEntity(apiException);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        APIError apiException = new APIError(HttpStatus.NOT_FOUND);
        apiException.setMessage(ex.getMessage());
        return buildResponseEntity(apiException);
    }
}