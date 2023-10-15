package com.byakko.common.domain.exception.handler;

import com.byakko.common.application.dto.ErrorDTO;
import com.byakko.common.application.dto.ErrorDetailDTO;
import com.byakko.common.application.dto.ErrorResponse;
import com.byakko.common.domain.exception.DomainException;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.common.domain.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        ErrorResponse response = ErrorResponse.Builder.builder()
                .error(ErrorDTO.Builder.builder()
                        .code(HttpStatus.NOT_FOUND.toString())
                        .message("Not found")
                        .errors(List.of(ErrorDetailDTO.Builder.builder()
                                        .message(ex.getMessage())
                                        .domain("")
                                        .reason(ex.getCause().getMessage())
                                .build()))
                        .build())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        ErrorResponse response = ErrorResponse.Builder.builder()
                .error(ErrorDTO.Builder.builder()
                        .code(HttpStatus.BAD_REQUEST.toString())
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

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleDomainException(DomainException ex) {
        ErrorResponse response = ErrorResponse.Builder.builder()
                .error(ErrorDTO.Builder.builder()
                        .code(HttpStatus.NOT_FOUND.toString())
                        .message("Not found")
                        .errors(List.of(ErrorDetailDTO.Builder.builder()
                                .message(ex.getMessage())
                                .domain("")
                                .reason(ex.getCause().getMessage())
                                .build()))
                        .build())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        ErrorResponse response = ErrorResponse.Builder.builder()
                .error(ErrorDTO.Builder.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .message("Internal Server Error")
                        .errors(List.of(ErrorDetailDTO.Builder.builder()
                                .message(ex.getMessage())
                                .domain("")
                                .reason(ex.getCause().getMessage())
                                .build()))
                        .build())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

}
