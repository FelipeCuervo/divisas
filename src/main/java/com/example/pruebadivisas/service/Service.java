package com.example.pruebadivisas.service;

import com.example.pruebadivisas.dto.ConversionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@org.springframework.stereotype.Service
public class Service {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String API_URL = "http://api.exchangeratesapi.io/v1/latest?access_key=10124780aa73c83cd1e5b667cf8af774";

    public Map<String, Object> getRates() {
        ResponseEntity<Map> response = restTemplate.getForEntity(API_URL, Map.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new RuntimeException("No se pudo obtener la tasa de cambio");
    }

    public ConversionDto convertidor(BigDecimal monto, String origen, String destino) {

        Map<String, Object> data = getRates();
        Map<String, BigDecimal> rates = (Map<String, BigDecimal>) data.get("rates");
        String date = (String) data.get("date");

        // Validar si las monedas existen
        if (!rates.containsKey(origen)) {
            throw new IllegalArgumentException("Moneda origen no válida: " + origen);
        }
        if (!rates.containsKey(destino)) {
            throw new IllegalArgumentException("Moneda destino no válida: " + destino);
        }

        // convertir los valores de las tasas a BigDecimal
        BigDecimal tasaOrigen = BigDecimal.valueOf(((Number) rates.get(origen)).doubleValue());
        BigDecimal tasaDestino = BigDecimal.valueOf(((Number) rates.get(destino)).doubleValue());

        // Obtener la tasa de conversión
        BigDecimal tasa =  tasaDestino.divide(tasaOrigen,6,RoundingMode.HALF_UP);
        // Operar monto * tasa para obtener valor de conversión
        BigDecimal result = monto.multiply(tasa.setScale(2,RoundingMode.HALF_UP));

        // Retornar Objeto de conversión
        return new ConversionDto(date, tasa, monto, result, origen, destino);
    }

}
