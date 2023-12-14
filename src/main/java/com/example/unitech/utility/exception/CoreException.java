package com.example.unitech.utility.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PROTECTED)
public class CoreException extends RuntimeException {
    int statusCode = HttpStatus.BAD_REQUEST.value();
    String message;
    String messageCode;

    public CoreException(String message) {
        super(message);
        this.message = message;
    }

}

