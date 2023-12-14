package com.example.unitech.utility.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseException {
    int status;
    String error;
    String messageCode;
    String lang;
    String message;
    String errorDetail;
    String path;
    String timestamp;
}
