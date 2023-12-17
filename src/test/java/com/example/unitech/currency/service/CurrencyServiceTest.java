package com.example.unitech.currency.service;

import com.example.unitech.currency.dto.CurrencyDTO;
import com.example.unitech.currency.dto.CurrencyRequestDTO;
import com.example.unitech.currency.model.ExchangeRate;
import com.example.unitech.currency.model.ExchangeType;
import com.example.unitech.custom_exception.CurrencyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @Mock
    private List<ExchangeType> exchangeTypes;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    public void setUp() {
        List<CurrencyDTO> currencies = Arrays.asList(
                new CurrencyDTO("US Dollar", "USD", "1.7"),
                new CurrencyDTO("Euro", "EUR", "2.0"),
                new CurrencyDTO("British Pound", "GBP", "2.3")
        );
    }

    @Test
    public void testGetCurrencyDataWhenFromAndToCurrencyAreSameThenReturnOne() {
        CurrencyRequestDTO requestDTO = new CurrencyRequestDTO("USD", "USD");
        String result = currencyService.getCurrencyData(requestDTO);
        assertEquals("USD/USD = 1.0", result);
    }

    @Test
    public void testGetCurrencyDataWhenFromCurrencyIsAZNAndToCurrencyIsNotAZNThenReturnCorrectExchangeRate() {
        CurrencyRequestDTO requestDTO = new CurrencyRequestDTO("AZN", "USD");
        String result = currencyService.getCurrencyData(requestDTO);
        assertEquals("AZN/USD = 0.59", result);
    }

    @Test
    public void testGetCurrencyDataWhenToCurrencyIsAZNAndFromCurrencyIsNotAZNThenReturnCorrectExchangeRate() {
        CurrencyRequestDTO requestDTO = new CurrencyRequestDTO("USD", "AZN");
        String result = currencyService.getCurrencyData(requestDTO);
        assertEquals("USD/AZN = 1.7", result);
    }

    @Test
    public void testGetCurrencyDataWhenBothFromAndToCurrencyAreNotAZNThenReturnCorrectExchangeRate() {
        CurrencyRequestDTO requestDTO = new CurrencyRequestDTO("EUR", "USD");
        String result = currencyService.getCurrencyData(requestDTO);
        assertEquals("EUR/USD = 1.1", result);
    }

    @Test
    public void testGetCurrencyDataWhenFromCurrencyNotFoundThenThrowCurrencyNotFoundException() {
        CurrencyRequestDTO requestDTO = new CurrencyRequestDTO("TEST", "USD");
        assertThrows(CurrencyNotFoundException.class, () -> currencyService.getCurrencyData(requestDTO));
    }

    @Test
    public void testGetCurrencyDataWhenToCurrencyNotFoundThenThrowCurrencyNotFoundException() {
        CurrencyRequestDTO requestDTO = new CurrencyRequestDTO("USD", "TEST");
        assertThrows(CurrencyNotFoundException.class, () -> currencyService.getCurrencyData(requestDTO));
    }
}