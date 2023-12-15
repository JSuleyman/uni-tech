package com.example.unitech.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class BalanceIncrementerRequestDTO {
    @NotNull(message = "Account number is required")
    Long accountNumber;
    @NotNull(message = "Amount is required")
    Double amount;
}
