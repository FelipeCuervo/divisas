package com.example.pruebadivisas.controller;

import com.example.pruebadivisas.dto.ConversionDto;
import com.example.pruebadivisas.dto.ErrorPersonalizado;
import com.example.pruebadivisas.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/conversion")
public class ControladorConversion {

    private final Service service;

    public ControladorConversion(Service service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> convertirMoneda(
            @RequestParam(required = true) BigDecimal monto,
            @RequestParam(required = true) String monedaOrigen,
            @RequestParam(required = true) String monedaDestino) {
        try {

            if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El monto debe ser un número positivo.");
            }

            if (monedaOrigen == null || monedaOrigen.isBlank()) {
                throw new IllegalArgumentException("La moneda de origen es obligatoria.");
            }
            if (monedaOrigen.length()>3) {
                throw new IllegalArgumentException("Código de moneda origen erroneo ");
            }

            if (monedaDestino == null || monedaDestino.isBlank()) {
                throw new IllegalArgumentException("La moneda de destino es obligatoria.");
            }

            if (monedaDestino.length()>3) {
                throw new IllegalArgumentException("Código de moneda destino erroneo ");
            }
            ConversionDto respuesta = service.convertidor(monto, monedaOrigen.toUpperCase(), monedaDestino.toUpperCase());
            return ResponseEntity.ok(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorPersonalizado(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorPersonalizado("Error en el servidor"));
        }
    }
}
