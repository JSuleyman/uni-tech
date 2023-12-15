package com.example.unitech.service;

import com.example.unitech.custom_exception.DuplicateAccountNumberException;
import com.example.unitech.dto.request.UserAccountCreateRequestDTO;
import com.example.unitech.entity.UserAccount;
import com.example.unitech.mapper.UserAccountMapper;
import com.example.unitech.repository.UserAccountRepository;
import com.example.unitech.security.custom_exception.UserNotFoundException;
import com.example.unitech.security.entity.User;
import com.example.unitech.security.repository.UserRepository;
import com.example.unitech.utility.SessionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAccountCreateServiceTest { //TODO duzdumu deye yoxla

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private UserAccountMapper userAccountMapper;

    @Mock
    private SessionManager sessionManager;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserAccountCreateService userAccountCreateService;

    private UserAccountCreateRequestDTO createRequestDTO;
    private UserAccount userAccount;
    private User user;

    @BeforeEach
    public void setUp() {
        createRequestDTO = new UserAccountCreateRequestDTO("Test Account", 1234567890123456L);
        userAccount = new UserAccount();
        user = new User();
    }

    @Test
    public void testCreateUserAccountWhenAccountNumberExistsThenThrowDuplicateAccountNumberException() {
        when(userAccountRepository.existsByAccountNumber(createRequestDTO.getAccountNumber())).thenReturn(true);

        assertThrows(DuplicateAccountNumberException.class, () -> userAccountCreateService.createUserAccount(createRequestDTO));

        verify(userAccountRepository, times(1)).existsByAccountNumber(createRequestDTO.getAccountNumber());
    }

    @Test
    public void testCreateUserAccountWhenUserNotFoundThenThrowUserNotFoundException() {
        when(userAccountRepository.existsByAccountNumber(createRequestDTO.getAccountNumber())).thenReturn(false);
        when(userRepository.findById(sessionManager.getCurrentUserId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userAccountCreateService.createUserAccount(createRequestDTO));

        verify(userAccountRepository, times(1)).existsByAccountNumber(createRequestDTO.getAccountNumber());
        verify(userRepository, times(1)).findById(sessionManager.getCurrentUserId());
    }

    @Test
    public void testCreateUserAccountWhenAccountNumberNotExistsAndUserFoundThenSaveUserAccount() {
        when(userAccountRepository.existsByAccountNumber(createRequestDTO.getAccountNumber())).thenReturn(false);
        when(userRepository.findById(sessionManager.getCurrentUserId())).thenReturn(Optional.of(user));
        when(userAccountMapper.userAccountCreateDtoToUserAccount(createRequestDTO)).thenReturn(userAccount);

        userAccountCreateService.createUserAccount(createRequestDTO);

        verify(userAccountRepository, times(1)).existsByAccountNumber(createRequestDTO.getAccountNumber());
        verify(userRepository, times(1)).findById(sessionManager.getCurrentUserId());
        verify(userAccountMapper, times(1)).userAccountCreateDtoToUserAccount(createRequestDTO);
        verify(userAccountRepository, times(1)).save(userAccount);
    }
}