package com.github.polijun.wavup.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiExceptionDto {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus statusCode;
    private final LocalDateTime timestamp;
}
