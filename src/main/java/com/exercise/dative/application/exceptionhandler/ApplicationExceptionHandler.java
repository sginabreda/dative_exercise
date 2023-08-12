package com.exercise.dative.application.exceptionhandler;


import com.exercise.dative.application.dto.response.ApiErrorDto;
import com.exercise.dative.domain.exception.ProductNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<ApiErrorDto> handleProductNotFoundException(
            ProductNotFoundException nfe,
            WebRequest request) {
        ApiErrorDto error = new ApiErrorDto(ErrorCode.NOT_FOUND.code, nfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        ApiErrorDto error = new ApiErrorDto(ErrorCode.BAD_REQUEST.code, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        ApiErrorDto error = new ApiErrorDto(ErrorCode.BAD_REQUEST.code, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request
    ) {
        LOGGER.error("Exception in request", ex);
        ApiErrorDto error = new ApiErrorDto(ErrorCode.INTERNAL_ERROR.code, "Something went wrong!");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    private enum ErrorCode {
        NOT_FOUND("not.found"),
        BAD_REQUEST("bad.request"),
        INTERNAL_ERROR("internal.server.error");

        private final String code;

        ErrorCode(String code) {
            this.code = code;
        }
    }
}
