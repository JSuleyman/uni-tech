package com.example.unitech.utility.exception;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Log4j2
public class CustomExceptionCreateService {
    CustomExceptionRepository customExceptionRepository;

    public void addException(CustomExceptionCreateDTO dto) {
        dto.getMessagesAndLangs().forEach((lang, message) ->
                customExceptionRepository.save(CustomException.builder()
                        .errorCode(dto.getErrorCode())
                        .lang(lang.trim().toUpperCase())
                        .message(message.trim())
                        .createdBy(dto.getCreatedBy().trim())
                        .build()));
    }
}