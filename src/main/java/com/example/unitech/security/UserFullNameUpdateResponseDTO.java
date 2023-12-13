package com.example.unitech.security;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserFullNameUpdateResponseDTO {
    String firstName;
    String lastName;
}
