package com.example.unitech.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserEmailVerificationServiceImpl implements UserEmailVerificationService {

    private final UserEmailVerificationRepository userEmailVerificationRepository;
    private final UserRepository userRepository;
    private final EmailSenderServiceImpl emailSenderService;

    @Override
    public void save(UserEmailVerification userEmailVerification) {
        userEmailVerificationRepository.save(userEmailVerification);
    }

    @Override
    public String verifyUser(String email, String verificationCode) {
        User user = userRepository.findByEmail(email).stream()
                .findFirst()
                .orElseThrow(NotFoundUser::new);

        UserEmailVerification userEmailVerification = userEmailVerificationRepository.userIsVerified(user.getId());

        LocalDateTime codeCreatedAt = userEmailVerification.getVerificationCodeCreatedAt();
        int codeExpirationMinutes = userEmailVerification.getVerificationCodeExpirationMinutes();

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = codeCreatedAt.plusMinutes(codeExpirationMinutes);

        if (currentTime.isAfter(expirationTime)) {
            userEmailVerification.setHasExpired(true);
            userEmailVerificationRepository.save(userEmailVerification);
            // Doğrulama kodu süresi geçmişse, kullanıcıyı uyarın veya kodun yeniden gönderilmesini sağlayın.
            throw new VerificationCodeHasExpired();
        }

        if (userEmailVerification.getVerificationCode().equals(verificationCode)) {
            // Doğrulama kodu doğruysa, kullanıcıyı doğrulama işlemi gerçekleştirin
            user.setVerified(true);
            userRepository.save(user);

            userEmailVerification.setHasExpired(true);
            userEmailVerificationRepository.save(userEmailVerification);
            return "User email verification successful.";
        } else {
            throw new InvalidVerificationCode();
        }
    }

    @Override
    public void repeatSendVerificationCode(String email) {
        User user = userRepository.findByEmail(email).stream()
                .findFirst()
                .orElseThrow(NotFoundUser::new);

        if (!user.isVerified()) {
            String verificationCode = VerificationCodeGenerator.generateVerificationCode();

            List<UserEmailVerification> list = userEmailVerificationRepository.listUserById(user.getId());
            for (UserEmailVerification userEmailVerification : list) {
                userEmailVerification.setHasExpired(true);
                save(userEmailVerification);
            }

            var savedUserEmailVerification = UserEmailVerification.builder()
                    .verificationCode(verificationCode)
                    .user(user)
                    .verificationCodeCreatedAt(LocalDateTime.now())
                    .verificationCodeExpirationMinutes(1)
                    .hasExpired(false)
                    .build();
            save(savedUserEmailVerification);

            emailSenderService.sendEmail(email,
                    "Verification Email code",
                    verificationCode);
        }
    }
}
