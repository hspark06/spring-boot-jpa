package com.jpa.springboot.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jpa.springboot.enums.MembershipErrorResult;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    // * Exception 종류
    // 1. BindException 
    //  – This exception is thrown when fatal binding errors occur.
    // 2. MethodArgumentNotValidException 
    //  – This exception is thrown when an argument annotated with @Valid failed validation
    // 3. MissingServletRequestPartException 
    //  - This exception is thrown when the part of a multipart request is not found.
    // 4. MissingServletRequestParameterException  
    //  - This exception is thrown when the request is missing a parameter
    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) 
    {
        final List<String> errorList = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        log.warn("Invalid DTO Parameter errors : {}", errorList);
        return this.makeErrorResponseEntity(errorList.toString());
        //return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @ExceptionHandler({MembershipException.class})
    public ResponseEntity<ErrorResponse> handleRestApiException(final MembershipException exception) {
        log.warn("MembershipException occur : ", exception);
        return this.makeErrorResponseEntity(exception.getErrorResult());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleException(final Exception exception) {
        log.warn("Exception occur: ", exception);
        return this.makeErrorResponseEntity(MembershipErrorResult.UNKNOWN_EXCEPTION);
    }

    // 1. Create ErrorResponse with String.
    private ResponseEntity<Object> makeErrorResponseEntity(final String errorDescription) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), errorDescription));
    }

    // 2. Create ErrorResponse with MembershipErrorResult
    private ResponseEntity<ErrorResponse> makeErrorResponseEntity(final MembershipErrorResult errorResult) {
        return ResponseEntity.status(errorResult.getHttpStatus())
                .body(new ErrorResponse(errorResult.name(), errorResult.getMessage()));
    }

    @Getter
    @RequiredArgsConstructor
    static class ErrorResponse {
        private final String code;
        private final String message;
    }
}
