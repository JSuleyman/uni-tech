package com.example.unitech.controller;

import com.example.unitech.dto.request.BalanceIncrementerRequestDTO;
import com.example.unitech.dto.request.UserAccountCreateRequestDTO;
import com.example.unitech.dto.request.UserAccountTransferManagerRequestDTO;
import com.example.unitech.dto.response.UserAccountViewerResponseDTO;
import com.example.unitech.service.UserAccountBalanceIncrementerService;
import com.example.unitech.service.UserAccountCreateService;
import com.example.unitech.service.UserAccountTransferManagerService;
import com.example.unitech.service.UserAccountViewerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-account")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAccountController {
    UserAccountCreateService createService;
    UserAccountViewerService userAccountViewerService;
    UserAccountBalanceIncrementerService balanceIncrementer;
    UserAccountTransferManagerService transferManagerService;

    @PostMapping
    public ResponseEntity<?> createUserAccount(@RequestBody
                                               @Valid
                                               UserAccountCreateRequestDTO createRequestDTO) {
        createService.createUserAccount(createRequestDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserAccountViewerResponseDTO>> getActiveUserAccounts() {
        return ResponseEntity.ok(userAccountViewerService.getActiveUserAccounts());
    }

    @PostMapping("/increment-balance")
    public ResponseEntity<Double> incrementBalance(@RequestBody
                                                   @Valid
                                                   BalanceIncrementerRequestDTO balanceIncrementerRequestDTO) {
        return ResponseEntity.ok(balanceIncrementer.incrementBalance(balanceIncrementerRequestDTO));
    }

    @PostMapping("/money-transfer")
    public ResponseEntity<Double> moneyTransfer(@RequestBody
                                                @Valid
                                                UserAccountTransferManagerRequestDTO moneyTransferRequestDTO) {
        return ResponseEntity.ok(transferManagerService.transferMoney(moneyTransferRequestDTO));
    }
}
