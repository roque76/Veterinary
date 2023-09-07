package com.pets.veterinaryprog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
//Los @'s son formas de generar getters setters de manera rapida
public class City {
    @NotEmpty
    private String code;
    @NotEmpty
    private String description;
}
