package com.example.unitech.currency.controller;

import com.example.unitech.currency.dto.CurrencyRequestDTO;
import com.example.unitech.currency.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class ExchangeController {
    private final CurrencyService currencyService;

    @GetMapping
    public String getCurrencyData(
            @RequestParam("from") String fromCurrency,
            @RequestParam("to") String toCurrency) {

        CurrencyRequestDTO requestDTO = buildCurrencyRequest(fromCurrency, toCurrency);
        return currencyService.getCurrencyData(requestDTO);
    }

    private CurrencyRequestDTO buildCurrencyRequest(String fromCurrency, String toCurrency) {
        return CurrencyRequestDTO.builder()
                .fromCurrency(fromCurrency)
                .toCurrency(toCurrency)
                .build();
    }

}
