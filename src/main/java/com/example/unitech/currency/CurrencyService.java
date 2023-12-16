package com.example.unitech.currency;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyService {

    public String getValyutaData(CurrencyRequestDTO requestDTO) {
        List<ExchangeType> valTypes = xmlToObject();
        List<ExchangeRate> exchanges = new ArrayList<>();

        valTypes.forEach(valType -> exchanges.addAll(valType.getValute()));

        List<CurrencyDTO> currencyDTOS = new ArrayList<>();
        exchanges.forEach(exchange -> {
            CurrencyDTO currencyDTO = CurrencyDTO.builder()
                    .valyutaAd(exchange.getName().substring(2))
                    .valyutaQisaAd(exchange.getCode())
                    .mezenne(exchange.getValue().toString())
                    .build();
            currencyDTOS.add(currencyDTO);
        });

        String fromValyuta = requestDTO.getFromValyuta(); //USD
        String toValyuta = requestDTO.getToValyuta(); //EUR

        if (fromValyuta.equals(toValyuta)) {
            return fromValyuta + "/" + toValyuta + " = " + 1.0;
        } else if (fromValyuta.equals("AZN")) {
            Double deger = 1 / currencyDTOS.stream()
                    .filter(currencyDTO -> currencyDTO.getValyutaQisaAd().equals(toValyuta))
                    .map(currencyDTO -> Double.parseDouble(currencyDTO.getMezenne()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Valyuta tapılmadı"));
            return fromValyuta + "/" + toValyuta + " = " + Math.round(deger * 100.0) / 100.0;
        } else if (toValyuta.equals("AZN")) {
            Double deger = currencyDTOS.stream()
                    .filter(currencyDTO -> currencyDTO.getValyutaQisaAd().equals(fromValyuta))
                    .map(currencyDTO -> Double.parseDouble(currencyDTO.getMezenne()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Valyuta tapılmadı"));
            return fromValyuta + "/" + toValyuta + " = " + Math.round(deger * 100.0) / 100.0;
        } else {
            Double fromValyutaMezenne = currencyDTOS.stream()
                    .filter(currencyDTO -> currencyDTO.getValyutaQisaAd().equals(fromValyuta))
                    .map(currencyDTO -> Double.parseDouble(currencyDTO.getMezenne()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Valyuta tapılmadı")); //0.59
            Double toValyutaMezenne = currencyDTOS.stream()
                    .filter(currencyDTO -> currencyDTO.getValyutaQisaAd().equals(toValyuta))
                    .map(currencyDTO -> Double.parseDouble(currencyDTO.getMezenne()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Valyuta tapılmadı")); //0.54
            double deger = fromValyutaMezenne / toValyutaMezenne;
            return fromValyuta + "/" + toValyuta + " = " + Math.round(deger * 100.0) / 100.0;
        }
    }

    @Scheduled(cron = "0 * * ? * *")
    private List<ExchangeType> xmlToObject() {
        LocalDate date = LocalDate.now();

        if (date.getDayOfWeek().name().equals("SATURDAY")) {
            date = date.minusDays(1);
        } else if (date.getDayOfWeek().name().equals("SUNDAY")) {
            date = date.minusDays(2);
        }

        String url = "https://www.cbar.az/currencies/" + date.format(DateTimeFormatter.ofPattern("dd.MM.YYYY")) + ".xml";

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<InputStream> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());

            if (response.statusCode() == 200) {
                InputStream responseBody = response.body();

                JAXBContext jaxbContext = JAXBContext.newInstance(ExchangeCours.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                ExchangeCours valCurs = (ExchangeCours) unmarshaller.unmarshal(responseBody);

                return valCurs.getValTypes()
                        .stream()
                        .filter(valType -> valType.getValType().equals("Xarici valyutalar"))
                        .collect(Collectors.toList());
            } else {
                System.out.println("HTTP isteği başarısız oldu. Hata kodu: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
