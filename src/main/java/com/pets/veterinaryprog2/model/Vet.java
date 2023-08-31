package com.pets.veterinaryprog2.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//Los @'s crean gets y sets rapidamente
public class Vet {
    private String code;
    private String name;
    private byte age;
}
