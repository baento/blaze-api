package fr.blaze.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class APIException {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<APISubException> subErrors;
 
    public APIException() {
        timestamp = LocalDateTime.now();
    }
 
    public APIException(HttpStatus status) {
        this();
        this.status = status;
    }
 
    public APIException(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public APIException(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }
 
    public APIException(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public static abstract class APISubException {

    }
}
