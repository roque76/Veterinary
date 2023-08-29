package com.pets.veterinaryprog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//Los @'s son formas de generar getters setters de manera rapida
public class City {
    private String code;
    private String description;
}
