package com.example.unitech.security;

public interface UserService {
    User getCurrentUser();

    UserFullNameUpdateResponseDTO updateUserFullName(UserFullNameUpdateRequestDTO userFullNameUpdateRequestDTO);
}
