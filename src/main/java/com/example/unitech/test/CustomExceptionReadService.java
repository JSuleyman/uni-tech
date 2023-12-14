package com.example.unitech.test;

import com.example.unitech.utility.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
@Log4j2
public class CustomExceptionReadService {
    SessionManager sessionManager;
    CustomExceptionRepository customExceptionRepository;

    public String getExceptionMessage(String exceptionCode, String lang) {

        lang = lang == null || lang.isBlank() ? sessionManager.DEFAULT_LANG : lang.toUpperCase(Locale.ROOT);

        return customExceptionRepository.findByErrorCodeAndLang(exceptionCode, lang)
                .orElse(CustomException.builder()
                        .message("Exception code or language code not found")
                        .build()).getMessage();

    }

}