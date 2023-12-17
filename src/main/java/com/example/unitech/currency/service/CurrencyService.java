package com.example.unitech.currency.service;

import com.example.unitech.currency.dto.CurrencyDTO;
import com.example.unitech.currency.dto.CurrencyRequestDTO;
import com.example.unitech.currency.model.ExchangeCours;
import com.example.unitech.currency.model.ExchangeRate;
import com.example.unitech.currency.model.ExchangeType;
import com.example.unitech.custom_exception.CurrencyNotFoundException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyService {

    public String getCurrencyData(CurrencyRequestDTO requestDTO) {
        List<ExchangeType> exchangeTypes = retrieveExchangeData();
        List<CurrencyDTO> currencies = prepareCurrencies(exchangeTypes);

        String fromCurrency = requestDTO.getFromCurrency();
        String toCurrency = requestDTO.getToCurrency();

        if (fromCurrency.equals(toCurrency)) {
            return fromCurrency + "/" + toCurrency + " = " + 1.0;
        }

        Double exchangeRate;
        if (fromCurrency.equals("AZN")) {
            exchangeRate = 1 / getRateForCurrency(toCurrency, currencies);
        } else if (toCurrency.equals("AZN")) {
            exchangeRate = getRateForCurrency(fromCurrency, currencies);
        } else {
            Double fromRate = getRateForCurrency(fromCurrency, currencies);
            Double toRate = getRateForCurrency(toCurrency, currencies);
            exchangeRate = fromRate / toRate;
        }

        return fromCurrency + "/" + toCurrency + " = " + Math.round(exchangeRate * 100.0) / 100.0;
    }

    @Scheduled(cron = "0 * * ? * *")
    private List<ExchangeType> retrieveExchangeData() {
        LocalDate adjustedDate = adjustDateForWeekend(LocalDate.now());

        String url = "https://www.cbar.az/currencies/" + formatDate(adjustedDate) + ".xml";

        try {
            HttpResponse<InputStream> response = makeHttpRequest(url);

            if (response.statusCode() == 200) {
                return extractExchangeTypes(response);
            } else {
                handleHttpRequestError(response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            handleRequestException(e);
        }

        return Collections.emptyList();
    }

    private List<CurrencyDTO> prepareCurrencies(List<ExchangeType> exchangeTypes) {
        return exchangeTypes.stream()
                .flatMap(exchangeType -> exchangeType.getValute().stream()
                        .map(this::createCurrencyDTO))
                .collect(Collectors.toList());
    }

    private CurrencyDTO createCurrencyDTO(ExchangeRate exchange) {
        return CurrencyDTO.builder()
                .currencyName(exchange.getName().substring(2))
                .currencyCode(exchange.getCode())
                .rate(exchange.getValue().toString())
                .build();
    }

    private Double getRateForCurrency(String currencyCode, List<CurrencyDTO> currencies) {
        return currencies.stream()
                .filter(currencyDTO -> currencyDTO.getCurrencyCode().equals(currencyCode))
                .map(currencyDTO -> Double.parseDouble(currencyDTO.getRate()))
                .findFirst()
                .orElseThrow(CurrencyNotFoundException::new);
    }

    private LocalDate adjustDateForWeekend(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return date.minusDays(1);
        } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return date.minusDays(2);
        }
        return date;
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));
    }

    private HttpResponse<InputStream> makeHttpRequest(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());
    }

    private List<ExchangeType> extractExchangeTypes(HttpResponse<InputStream> response) {
        try (InputStream responseBody = response.body()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(ExchangeCours.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ExchangeCours valCurs = (ExchangeCours) unmarshaller.unmarshal(responseBody);

            return valCurs.getValTypes().stream()
                    .filter(valType -> valType.getValType().equalsIgnoreCase("Xarici valyutalar"))
                    .collect(Collectors.toList());
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleHttpRequestError(int statusCode) {
        System.out.println("HTTP request failed. Error code: " + statusCode);
    }

    private void handleRequestException(Exception e) {
        e.printStackTrace();
    }

}
