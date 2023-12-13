package com.example.unitech.security;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFullNameUpdateRequestDTO {
    String firstName;
    String lastName;
}
