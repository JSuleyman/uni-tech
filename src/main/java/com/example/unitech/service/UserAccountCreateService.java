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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserAccountCreateService {
    UserAccountRepository userAccountRepository;
    UserAccountMapper userAccountMapper;
    SessionManager sessionManager;
    UserRepository userRepository;

    public void createUserAccount(UserAccountCreateRequestDTO createRequestDTO) {
        if (userAccountRepository.existsByAccountNumber(createRequestDTO.getAccountNumber())) {
            throw new DuplicateAccountNumberException();
        }
        User user = userRepository
                .findById(sessionManager.getCurrentUserId())
                .orElseThrow(UserNotFoundException::new);

        UserAccount userAccountEntity = userAccountMapper.userAccountCreateDtoToUserAccount(createRequestDTO);
        userAccountEntity.setUser(user);
        userAccountRepository.save(userAccountEntity);
    }
}
