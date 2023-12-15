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
public class UserAccountTransferManagerRequestDTO { //TODO notnull ve notblanklari yoxla
    @NotNull(message = "From account number is required")
    Long fromAccountNumber;
    @NotNull(message = "To account number is required")
    Long toAccountNumber;
    @NotNull(message = "Amount is required")
    Double amount;
}
