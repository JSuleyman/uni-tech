package com.example.unitech.test;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CustomExceptionCreateDTO {
        String createdBy;
        String errorCode;
        Map<String, String> messagesAndLangs;
}