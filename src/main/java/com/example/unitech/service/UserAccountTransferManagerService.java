package com.example.unitech.service;

import com.example.unitech.custom_exception.AccountNotFoundException;
import com.example.unitech.custom_exception.InactiveAccountTransferException;
import com.example.unitech.custom_exception.InsufficientBalanceException;
import com.example.unitech.custom_exception.SameAccountTransferException;
import com.example.unitech.dto.request.UserAccountTransferManagerRequestDTO;
import com.example.unitech.entity.UserAccount;
import com.example.unitech.enums.AccountStatus;
import com.example.unitech.repository.UserAccountRepository;
import com.example.unitech.utility.SessionManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserAccountTransferManagerService {
    UserAccountRepository userAccountRepository;
    SessionManager sessionManager;

    public Double transferMoney(UserAccountTransferManagerRequestDTO requestDTO) {
        UserAccount fromAccount = getUserAccountByAccountNumberAndStatus(requestDTO.getFromAccountNumber());
        UserAccount toAccount = getUserAccountByAccountNumber(requestDTO.getToAccountNumber());

        validateTransferConditions(fromAccount, toAccount, requestDTO.getAmount());

        performMoneyTransfer(fromAccount, toAccount, requestDTO.getAmount());

        return fromAccount.getAccountBalance();
    }

    private UserAccount getUserAccountByAccountNumberAndStatus(Long accountNumber) {
        return userAccountRepository
                .findByUser_IdAndAccountNumberAndAccountStatus(
                        sessionManager.getCurrentUserId(),
                        accountNumber,
                        AccountStatus.ACTIVE
                ).orElseThrow(AccountNotFoundException::new);
    }

    private UserAccount getUserAccountByAccountNumber(Long accountNumber) {
        return userAccountRepository
                .findByUser_IdAndAccountNumber(
                        sessionManager.getCurrentUserId(),
                        accountNumber
                ).orElseThrow(AccountNotFoundException::new);
    }

    private void validateTransferConditions(UserAccount fromAccount, UserAccount toAccount, double amount) {
        if (toAccount.getAccountStatus() == AccountStatus.DEACTIVE) {
            throw new InactiveAccountTransferException();
        }

        if (fromAccount.getAccountNumber().equals(toAccount.getAccountNumber())) {
            throw new SameAccountTransferException();
        }

        if (fromAccount.getAccountBalance() < amount) {
            throw new InsufficientBalanceException();
        }
    }

    private void performMoneyTransfer(UserAccount fromAccount, UserAccount toAccount, double amount) {
        fromAccount.setAccountBalance(fromAccount.getAccountBalance() - amount);
        toAccount.setAccountBalance(toAccount.getAccountBalance() + amount);

        saveAccounts(fromAccount, toAccount);
    }

    private void saveAccounts(UserAccount... accounts) {
        userAccountRepository.saveAll(List.of(accounts));
    }
}
