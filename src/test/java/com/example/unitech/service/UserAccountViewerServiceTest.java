package com.example.unitech.service;

import com.example.unitech.dto.response.UserAccountViewerResponseDTO;
import com.example.unitech.entity.UserAccount;
import com.example.unitech.enums.AccountStatus;
import com.example.unitech.mapper.UserAccountMapper;
import com.example.unitech.repository.UserAccountRepository;
import com.example.unitech.utility.SessionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAccountViewerServiceTest { //TODO duzdumu deye yoxla

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private UserAccountMapper userAccountMapper;

    @Mock
    private SessionManager sessionManager;

    @InjectMocks
    private UserAccountViewerService userAccountViewerService;

    private final String currentUserId = "testUserId";

    @BeforeEach
    public void setUp() {
        when(sessionManager.getCurrentUserId()).thenReturn(currentUserId);
    }

    @Test
    public void testGetActiveUserAccountsWhenActiveUserAccountsExistThenReturnUserAccountViewerResponseDTOs() {
        // Arrange
        List<UserAccount> activeUserAccounts = Collections.singletonList(new UserAccount());
        List<UserAccountViewerResponseDTO> expectedResponse = Collections.singletonList(new UserAccountViewerResponseDTO());
        when(userAccountRepository.findAllByUser_IdAndAccountStatus(currentUserId, AccountStatus.ACTIVE)).thenReturn(activeUserAccounts);
        when(userAccountMapper.userAccountsToUserAccountViewerResponseDTOs(activeUserAccounts)).thenReturn(expectedResponse);

        // Act
        List<UserAccountViewerResponseDTO> actualResponse = userAccountViewerService.getActiveUserAccounts();

        // Assert
        verify(userAccountRepository, times(1)).findAllByUser_IdAndAccountStatus(currentUserId, AccountStatus.ACTIVE);
        verify(userAccountMapper, times(1)).userAccountsToUserAccountViewerResponseDTOs(activeUserAccounts);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    public void testGetActiveUserAccountsWhenNoActiveUserAccountsExistThenReturnEmptyList() {
        // Arrange
        List<UserAccount> activeUserAccounts = Collections.emptyList();
        List<UserAccountViewerResponseDTO> expectedResponse = Collections.emptyList();
        when(userAccountRepository.findAllByUser_IdAndAccountStatus(currentUserId, AccountStatus.ACTIVE)).thenReturn(activeUserAccounts);
        when(userAccountMapper.userAccountsToUserAccountViewerResponseDTOs(activeUserAccounts)).thenReturn(expectedResponse);

        // Act
        List<UserAccountViewerResponseDTO> actualResponse = userAccountViewerService.getActiveUserAccounts();

        // Assert
        verify(userAccountRepository, times(1)).findAllByUser_IdAndAccountStatus(currentUserId, AccountStatus.ACTIVE);
        verify(userAccountMapper, times(1)).userAccountsToUserAccountViewerResponseDTOs(activeUserAccounts);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}