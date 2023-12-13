package com.example.unitech.security;

import com.example.unitech.utility.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SessionManager sessionManager;

    @Override
    public User getCurrentUser() {
        return userRepository.findByEmail(sessionManager.getCurrentUserName()).stream()
                .findFirst()
                .orElseThrow(NotFoundUser::new);
    }

    @Override
    public UserFullNameUpdateResponseDTO updateUserFullName(UserFullNameUpdateRequestDTO userFullNameUpdateRequestDTO) {
        User user = getCurrentUser();

        user.setFirstname(userFullNameUpdateRequestDTO.getFirstName());
        user.setLastname(userFullNameUpdateRequestDTO.getLastName());
        userRepository.save(user);

        return UserFullNameUpdateResponseDTO.builder()
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .build();
    }
}
