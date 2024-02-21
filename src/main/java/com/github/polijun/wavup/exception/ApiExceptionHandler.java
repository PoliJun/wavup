package com.github.polijun.wavup.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiExceptionDto apiExceptionDto = ApiExceptionDto.builder().message(e.getMessage())
                .statusCode(httpStatus).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.badRequest().body(apiExceptionDto);
    }
}
