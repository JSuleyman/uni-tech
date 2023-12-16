package com.example.unitech.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class ExchangeController {
    private final CurrencyService currencyService;

    @GetMapping
    public List<CurrencyDTO> getValyutaData() {
        return currencyService.getValyutaData();
    }
}
