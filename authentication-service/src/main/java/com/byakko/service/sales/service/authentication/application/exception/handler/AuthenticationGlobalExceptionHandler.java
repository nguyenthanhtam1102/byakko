package com.byakko.service.sales.service.authentication.application.exception.handler;

import com.byakko.service.sales.common.application.dto.ErrorDTO;
import com.byakko.service.sales.common.application.dto.ErrorResponse;
import com.byakko.service.sales.common.domain.exception.handler.GlobalExceptionHandler;
import com.byakko.service.sales.service.authentication.domain.domaincore.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthenticationGlobalExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex) {
        ErrorResponse response = ErrorResponse.Builder.builder()
                .error(ErrorDTO.Builder.builder()
                        .code(HttpStatus.FORBIDDEN.value())
                        .message(ex.getMessage())
                        .build())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

}
