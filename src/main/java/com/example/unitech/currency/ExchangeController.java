package com.example.unitech.currency;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
@Slf4j
public class ExchangeController {
    private final CurrencyService currencyService;

    @GetMapping
    public String getValyutaData(@RequestParam String fromValyuta, @RequestParam String toValyuta) {
        log.info(fromValyuta);
        log.info(toValyuta);
        return currencyService.getValyutaData(CurrencyRequestDTO.builder()
                .fromValyuta(fromValyuta)
                .toValyuta(toValyuta)
                .build());
    }
}