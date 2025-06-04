package com.example.pruebadivisas.dto;

import java.math.BigDecimal;

public class ConversionDto {

    private String fecha;
    private BigDecimal tasaCambio;
    private BigDecimal montoOrigen;
    private BigDecimal montoResultante;
    private String monedaOrigen;
    private String modenaDestino;

    public ConversionDto(String fecha, BigDecimal tasaCambio, BigDecimal montoOrigen, BigDecimal montoResultante, String monedaOrigen, String modenaDestino) {
        this.fecha = fecha;
        this.tasaCambio = tasaCambio;
        this.montoOrigen = montoOrigen;
        this.montoResultante = montoResultante;
        this.monedaOrigen = monedaOrigen;
        this.modenaDestino = modenaDestino;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTasaCambio() {
        return tasaCambio;
    }

    public void setTasaCambio(BigDecimal tasaCambio) {
        this.tasaCambio = tasaCambio;
    }

    public BigDecimal getMontoOrigen() {
        return montoOrigen;
    }

    public void setMontoOrigen(BigDecimal montoOrigen) {
        this.montoOrigen = montoOrigen;
    }

    public BigDecimal getMontoResultante() {
        return montoResultante;
    }

    public void setMontoResultante(BigDecimal montoResultante) {
        this.montoResultante = montoResultante;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public String getModenaDestino() {
        return modenaDestino;
    }

    public void setModenaDestino(String modenaDestino) {
        this.modenaDestino = modenaDestino;
    }
}
