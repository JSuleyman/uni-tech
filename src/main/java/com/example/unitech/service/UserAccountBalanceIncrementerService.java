package com.example.unitech.service;

import com.example.unitech.custom_exception.AccountNotFoundException;
import com.example.unitech.custom_exception.ValueOutOfRangeException;
import com.example.unitech.dto.request.BalanceIncrementerRequestDTO;
import com.example.unitech.entity.UserAccount;
import com.example.unitech.enums.AccountStatus;
import com.example.unitech.repository.UserAccountRepository;
import com.example.unitech.utility.SessionManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserAccountBalanceIncrementerService {
    UserAccountRepository userAccountRepository;
    SessionManager sessionManager;

    public Double incrementBalance(BalanceIncrementerRequestDTO requestDTO) {
        if (requestDTO.getAmount() <= 0.0 || requestDTO.getAmount() >= 1000.00) {
            throw new ValueOutOfRangeException();
        }
        UserAccount userAccount = userAccountRepository
                .findByUser_IdAndAccountNumberAndAccountStatus(
                        sessionManager.getCurrentUserId(),
                        requestDTO.getAccountNumber(),
                        AccountStatus.ACTIVE).orElseThrow(AccountNotFoundException::new);
        userAccount.setAccountBalance(userAccount.getAccountBalance() + requestDTO.getAmount());
        userAccountRepository.save(userAccount);
        return userAccount.getAccountBalance();
    }
}
