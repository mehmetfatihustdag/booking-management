package com.company.challenge.booking.exception.handler;

import com.company.challenge.booking.domain.dto.ErrorDTO;
import com.company.challenge.booking.exception.BusinessBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



/**
 * Exception Handler Advice
 * @author Fatih Ustdag
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler  {


    /**
     * Handle any Exception that extends from BusinessBadRequestException returning a HTTP status code BAD_REQUEST (400)
     *
     * @param e Exception to be handled
     * @return {@link ErrorDTO} containing the error message
     */
    @ExceptionHandler(BusinessBadRequestException.class)
    public ResponseEntity<ErrorDTO> handleBadRequestException(BusinessBadRequestException e) {
        final ErrorDTO error = new ErrorDTO(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Handle any unexpected Exception returning a HTTP status code INTERNAL_SERVER_ERROR (500)
     *
     * @param e Exception to be handled
     * @return {@link ErrorDTO} containing the error message
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDTO> handleThrowable(Throwable e) {
        final ErrorDTO error = new ErrorDTO(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
