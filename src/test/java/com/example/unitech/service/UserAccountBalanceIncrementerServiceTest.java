package com.example.unitech.service;

import com.example.unitech.custom_exception.InvalidAccountNumberException;
import com.example.unitech.custom_exception.ValueOutOfRangeException;
import com.example.unitech.dto.request.BalanceIncrementerRequestDTO;
import com.example.unitech.entity.UserAccount;
import com.example.unitech.repository.UserAccountRepository;
import com.example.unitech.utility.SessionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAccountBalanceIncrementerServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private SessionManager sessionManager;

    @InjectMocks
    private UserAccountBalanceIncrementerService userAccountBalanceIncrementerService;

    private BalanceIncrementerRequestDTO requestDTO;
    private UserAccount userAccount;

    @BeforeEach
    void setUp() {
        requestDTO = new BalanceIncrementerRequestDTO(1234567890123456L, 500.0);
        userAccount = new UserAccount();
        userAccount.setAccountNumber(1234567890123456L);
        userAccount.setAccountBalance(1000.0);
    }

    @Test
    @DisplayName("Test incrementBalance method with valid amount and account number")
    void testIncrementBalanceWhenAmountAndAccountNumberAreValidThenReturnUpdatedBalance() {
        when(sessionManager.getCurrentUserId()).thenReturn("1");
        when(userAccountRepository.findByUser_IdAndAccountNumberAndAccountStatus(anyString(), anyLong(), any())).thenReturn(Optional.of(userAccount));

        Double updatedBalance = userAccountBalanceIncrementerService.incrementBalance(requestDTO);

        assertThat(updatedBalance).isEqualTo(1500.0);
        verify(userAccountRepository, times(1)).save(userAccount);
    }

    @Test
    @DisplayName("Test incrementBalance method with amount less than or equal to 0")
    void testIncrementBalanceWhenAmountIsLessThanOrEqualToZeroThenThrowException() {
        requestDTO.setAmount(0.0);

        assertThatThrownBy(() -> userAccountBalanceIncrementerService.incrementBalance(requestDTO))
                .isInstanceOf(ValueOutOfRangeException.class);

        verify(userAccountRepository, never()).findByUser_IdAndAccountNumberAndAccountStatus(anyString(), anyLong(), any());
    }

    @Test
    @DisplayName("Test incrementBalance method with amount greater than or equal to 1000")
    void testIncrementBalanceWhenAmountIsGreaterThanOrEqualTo1000ThenThrowException() {
        requestDTO.setAmount(1000.0);

        assertThatThrownBy(() -> userAccountBalanceIncrementerService.incrementBalance(requestDTO))
                .isInstanceOf(ValueOutOfRangeException.class);

        verify(userAccountRepository, never()).findByUser_IdAndAccountNumberAndAccountStatus(anyString(), anyLong(), any());
    }

    @Test
    @DisplayName("Test incrementBalance method with invalid account number")
    void testIncrementBalanceWhenAccountNumberIsInvalidThenThrowException() {
        when(sessionManager.getCurrentUserId()).thenReturn("1");
        when(userAccountRepository.findByUser_IdAndAccountNumberAndAccountStatus(anyString(), anyLong(), any())).thenThrow(InvalidAccountNumberException.class);

        assertThatThrownBy(() -> userAccountBalanceIncrementerService.incrementBalance(requestDTO))
                .isInstanceOf(InvalidAccountNumberException.class);

        verify(userAccountRepository, times(1)).findByUser_IdAndAccountNumberAndAccountStatus(anyString(), anyLong(), any());
    }
}