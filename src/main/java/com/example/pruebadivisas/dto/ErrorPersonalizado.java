package com.example.pruebadivisas.dto;

public class ErrorPersonalizado {

    private String error;

    public ErrorPersonalizado(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
