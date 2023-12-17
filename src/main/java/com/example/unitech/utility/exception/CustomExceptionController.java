package com.example.unitech.utility.exception;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/custom-exception")
@RequiredArgsConstructor
public class CustomExceptionController {
    CustomExceptionCreateService customExceptionCreateService;

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createException(@RequestBody CustomExceptionCreateDTO dto) {
        customExceptionCreateService.addException(dto);
    }

}
