package com.example.unitech.utility.exception;

import com.example.unitech.test.CustomExceptionReadService;
import com.example.unitech.utility.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

@ControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class ExceptionHandlerCore extends ResponseEntityExceptionHandler {
    SessionManager sessionManager;
    CustomExceptionReadService customExceptionReadService;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptions(Exception ex, WebRequest request) {
        return buildResponseEntity(ex, request);
    }

    //    Helper Methods
    private ResponseEntity<?> getResponseEntity(Exception ex,
                                                HttpStatus status,
                                                WebRequest request,
                                                String message,
                                                int statusCode,
                                                String appError,
                                                String messageCode,
                                                String lang
    ) {

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ResponseException responseException = ResponseException.builder()
                .status(statusCode)
                .messageCode(messageCode)
                .lang(lang)
                .error(appError)
                .message(message)
                .errorDetail(ex.getMessage())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .timestamp(localDateTime.format(formatter))
                .build();
        return new ResponseEntity<>(responseException, status);
    }

    private ResponseEntity<?> buildResponseEntity(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String appError = status.getReasonPhrase();
        String messageCode = "";
        String lang = sessionManager.getLang();
        String message = "";
        int statusCode = 0;

        if (ex instanceof CoreException) {
            String extractedMessageCode = getMessageCode(ex).replace(" ", "");
            message = customExceptionReadService.getExceptionMessage(extractedMessageCode, sessionManager.getLang());
            status = HttpStatus.BAD_REQUEST;
            statusCode = parseInt(getStatusCode(ex));
            appError = "Application error";
            messageCode = extractedMessageCode;
            lang = getLang();
        }
        return getResponseEntity(ex, status, request, message, statusCode, appError, messageCode, lang);
    }

    private String getStatusCode(Exception ex) {
        if (ex instanceof CoreException) {
            return String.valueOf(((CoreException) ex).getStatusCode());
        } else {
            return "";
        }
    }

    private String getMessageCode(Exception ex) {
        if (ex instanceof CoreException) {
            return ((CoreException) ex).getMessageCode();
        }
        return "";
    }

    private String getLang() {
        return sessionManager.getLang();
    }
}