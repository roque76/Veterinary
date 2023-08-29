package com.pets.veterinaryprog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//Los @'s generan los meteodos necesarios para el funcionamineto de la clase
public class Vaccine {
    private City city;
    private Vet vet;
    private Short quantity;
}
