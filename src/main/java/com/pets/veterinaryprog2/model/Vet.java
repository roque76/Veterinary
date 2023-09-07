package com.pets.veterinaryprog2.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
//Los @'s crean gets y sets rapidamente
public class Vet {
    @NotEmpty
    private String code;
    @NotEmpty
    private String name;
    @Min(18)
    private Byte age;
}
