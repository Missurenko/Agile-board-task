package ua.com.mycompany.exeption;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ObjectByIdNotFoundExeption.class})
    public ResponseEntity<ApiError> handleNotFoundStatus(Exception ex, WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), ex.getSuppressed(), ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ObjectByNameExistExeption.class})
    public ResponseEntity<ApiError> handleConflictStatus(Exception ex, WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), ex.getSuppressed(), ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ServerErrorExeption.class})
    public ResponseEntity<ApiError> handleErrorServer(Exception ex, WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ex.getSuppressed(), ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}

