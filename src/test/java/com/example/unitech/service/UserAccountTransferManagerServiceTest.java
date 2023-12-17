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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAccountTransferManagerServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private SessionManager sessionManager;

    @InjectMocks
    private UserAccountTransferManagerService userAccountTransferManagerService;

    private UserAccountTransferManagerRequestDTO requestDTO;
    private UserAccount fromAccount;
    private UserAccount toAccount;

    @BeforeEach
    public void setUp() {
        requestDTO = new UserAccountTransferManagerRequestDTO(1234567890123456L, 6543210987654321L, 100.0);
        fromAccount = new UserAccount();
        fromAccount.setAccountNumber(requestDTO.getFromAccountNumber());
        fromAccount.setAccountBalance(200.0);
        fromAccount.setAccountStatus(AccountStatus.ACTIVE);
        toAccount = new UserAccount();
        toAccount.setAccountNumber(requestDTO.getToAccountNumber());
        toAccount.setAccountBalance(100.0);
        toAccount.setAccountStatus(AccountStatus.ACTIVE);

        String currentUserId = "testUserId";
        when(sessionManager.getCurrentUserId()).thenReturn(currentUserId);
    }

    @Test
    public void testTransferMoneyWhenTransferIsSuccessfulThenReturnFromAccountBalance() {
        when(userAccountRepository.findByUser_IdAndAccountNumberAndAccountStatus(anyString(), anyLong(), any()))
                .thenReturn(Optional.of(fromAccount));
        when(userAccountRepository.findByAccountNumber(anyLong()))
                .thenReturn(Optional.of(toAccount));

        Double result = userAccountTransferManagerService.transferMoney(requestDTO);

        assertEquals(100.0, result);
        verify(userAccountRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testTransferMoneyWhenFromAccountNotFoundThenThrowAccountNotFoundException() {
        when(userAccountRepository.findByUser_IdAndAccountNumberAndAccountStatus(anyString(), anyLong(), any()))
                .thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> userAccountTransferManagerService.transferMoney(requestDTO));
    }

    @Test
    public void testTransferMoneyWhenToAccountNotFoundThenThrowAccountNotFoundException() {
        when(userAccountRepository.findByUser_IdAndAccountNumberAndAccountStatus(anyString(), anyLong(), any()))
                .thenReturn(Optional.of(fromAccount));
        when(userAccountRepository.findByAccountNumber(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> userAccountTransferManagerService.transferMoney(requestDTO));
    }

    @Test
    public void testTransferMoneyWhenToAccountIsInactiveThenThrowInactiveAccountTransferException() {
        when(userAccountRepository.findByUser_IdAndAccountNumberAndAccountStatus(anyString(), anyLong(), any()))
                .thenReturn(Optional.of(fromAccount));
        toAccount.setAccountStatus(AccountStatus.DEACTIVE);
        when(userAccountRepository.findByAccountNumber(anyLong()))
                .thenReturn(Optional.of(toAccount));

        assertThrows(InactiveAccountTransferException.class, () -> userAccountTransferManagerService.transferMoney(requestDTO));
    }

    @Test
    public void testTransferMoneyWhenFromAndToAccountsAreSameThenThrowSameAccountTransferException() {
        toAccount.setAccountNumber(fromAccount.getAccountNumber());
        when(userAccountRepository.findByUser_IdAndAccountNumberAndAccountStatus(anyString(), anyLong(), any()))
                .thenReturn(Optional.of(fromAccount));
        when(userAccountRepository.findByAccountNumber(anyLong()))
                .thenReturn(Optional.of(toAccount));

        assertThrows(SameAccountTransferException.class, () -> userAccountTransferManagerService.transferMoney(requestDTO));
    }

    @Test
    public void testTransferMoneyWhenFromAccountHasInsufficientBalanceThenThrowInsufficientBalanceException() {
        fromAccount.setAccountBalance(50.0);
        when(userAccountRepository.findByUser_IdAndAccountNumberAndAccountStatus(anyString(), anyLong(), any()))
                .thenReturn(Optional.of(fromAccount));
        when(userAccountRepository.findByAccountNumber(anyLong()))
                .thenReturn(Optional.of(toAccount));

        assertThrows(InsufficientBalanceException.class, () -> userAccountTransferManagerService.transferMoney(requestDTO));
    }
}