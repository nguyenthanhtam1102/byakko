package com.byakko.service.sales.common.domain.exception.handler;

import com.byakko.service.sales.common.application.dto.ErrorDTO;
import com.byakko.service.sales.common.application.dto.ErrorDetailDTO;
import com.byakko.service.sales.common.application.dto.ErrorResponse;
import com.byakko.service.sales.common.domain.exception.DomainException;
import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.common.domain.exception.UnauthorizedException;
import com.byakko.service.sales.common.domain.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        ErrorResponse response = ErrorResponse.Builder.builder()
                .error(ErrorDTO.Builder.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .build())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String field = ((PathImpl) violation.getPropertyPath()).getLeafNode().asString();
            String message = violation.getMessage();
            errors.put(field, message);
        }

        ErrorResponse response = ErrorResponse.Builder.builder()
                .error(ErrorDTO.Builder.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Bad request")
                        .errors(errors.entrySet().stream().map(entry ->
                                        ErrorDetailDTO.Builder.builder()
                                                .message(entry.getKey())
                                                .domain("")
                                                .reason(entry.getValue())
                                                .build())
                                .collect(Collectors.toList()))
                        .build())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        ErrorResponse response = ErrorResponse.Builder.builder()
                .error(ErrorDTO.Builder.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Bad request")
                        .errors(ex.getErrors().entrySet().stream().map(entry ->
                                ErrorDetailDTO.Builder.builder()
                                        .message(entry.getKey())
                                        .domain("")
                                        .reason(entry.getValue())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex) {
        ErrorResponse response = ErrorResponse.Builder.builder()
                .error(ErrorDTO.Builder.builder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .message(ex.getMessage())
                        .build())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleDomainException(DomainException ex) {
        ErrorResponse response = ErrorResponse.Builder.builder()
                .error(ErrorDTO.Builder.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ex.getMessage())
                        .build())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        ErrorResponse response = ErrorResponse.Builder.builder()
                .error(ErrorDTO.Builder.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ex.getMessage())
                        .build())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

}
