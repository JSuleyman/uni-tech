package com.example.unitech.service;

import com.example.unitech.dto.response.UserAccountViewerResponseDTO;
import com.example.unitech.enums.AccountStatus;
import com.example.unitech.mapper.UserAccountMapper;
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
public class UserAccountViewerService {
    UserAccountRepository userAccountRepository;
    UserAccountMapper userAccountMapper;
    SessionManager sessionManager;

    public List<UserAccountViewerResponseDTO> getActiveUserAccounts() {
        return userAccountMapper.userAccountsToUserAccountViewerResponseDTOs(
                userAccountRepository.findAllByUser_IdAndAccountStatus(
                        sessionManager.getCurrentUserId(), AccountStatus.ACTIVE
                )
        );
    }
}
