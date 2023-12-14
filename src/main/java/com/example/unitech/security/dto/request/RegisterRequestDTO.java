package com.example.unitech.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequestDTO {
    @NotBlank(message = "Pin is not null")
    String pin;
    @NotBlank(message = "Password is not null")
    String password;
}
