package com.proshore.vpps.exceptionHandler;

import com.proshore.vpps.exceptionHandler.customException.EmptyListException;
import com.proshore.vpps.exceptionHandler.customException.ErrorMessage;
import com.proshore.vpps.exceptionHandler.customException.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationException(final ConstraintViolationException ex, final HttpServletRequest httpServletRequest) {
        ErrorMessage errorResponse = new ErrorMessage();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setApiPath(httpServletRequest.getRequestURI());
        ex.getConstraintViolations().forEach(violation -> errorResponse
                .getErrors().add(violation.getMessage()));
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<ExceptionResponseDTO> emptyListException(final EmptyListException ex, final HttpServletRequest request) {
        ExceptionResponseDTO error = new ExceptionResponseDTO(ex.getLocalizedMessage(), request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
