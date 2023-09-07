package com.pets.veterinaryprog2.controller;
import com.pets.veterinaryprog2.controller.dto.ResponseDTO;
import com.pets.veterinaryprog2.exceptions.VeterinaryException;
import com.pets.veterinaryprog2.model.City;
import com.pets.veterinaryprog2.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/cities")
public class CityController {
    @Autowired
    private CityService veterinaryService;

    @GetMapping(path="/getCities")
    public ResponseEntity<ResponseDTO> getAllCities(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                veterinaryService.getCities(), null),HttpStatus.OK);

    }
    @GetMapping(path="/byId/{id}")
    public ResponseEntity<ResponseDTO> getCityById(@PathVariable String id){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    veterinaryService.findCItyById(id), null), HttpStatus.OK);
        }
        catch (VeterinaryException e){
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());

            return new ResponseEntity<>(new ResponseDTO(HttpStatus.NOT_FOUND.value(),
                    null, errors), HttpStatus.OK);
        }
    }
    @GetMapping(path="byName/{name}")
    public ResponseEntity<ResponseDTO> getCityByName(@PathVariable String name){
        if(name != null && !name.isEmpty()){
            try{
                return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                        veterinaryService.findCityByName(name),null),HttpStatus.OK);
            }
            catch (VeterinaryException e){
                List<String> errors = new ArrayList<>();
                errors.add(e.getMessage());

                return new ResponseEntity<>(new ResponseDTO(HttpStatus.NOT_FOUND.value(),
                        null,errors),HttpStatus.OK);
            }
        }
        else{
            List<String> error = new ArrayList<>();
            error.add("EL nombre no puede estar vacio");
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,error),HttpStatus.OK);
        }
    }
    @GetMapping(path="byLetter/{letter}")
    public ResponseEntity<ResponseDTO> getCitiesByFirstLetter(@PathVariable char letter){
        if(Character.isLetter(letter)) {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    veterinaryService.findCitiesByFirstLetter(letter), null), HttpStatus.OK);
        }
        else{
            List<String> error = new ArrayList<>();
            error.add("El caracter proveido tiene que ser una letra");

            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,error),HttpStatus.OK);
        }

    }

    @PostMapping(path="/createCity")
    public ResponseEntity<ResponseDTO> createCity(@Valid @RequestBody City city){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    veterinaryService.createCity(city),null),HttpStatus.OK);
        } catch (VeterinaryException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());

            return new ResponseEntity<>(new ResponseDTO(HttpStatus.CONFLICT.value(),
                    null,errors),HttpStatus.OK);
        }
    }

    @PutMapping(path="updateCity/{id}")
    public ResponseEntity<ResponseDTO> updateCity(@PathVariable String id, @Valid @RequestBody City city){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    veterinaryService.updateCity(id,city),null),HttpStatus.OK);
        } catch (VeterinaryException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());

            return new ResponseEntity<>(new ResponseDTO(HttpStatus.NOT_FOUND.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidation(MethodArgumentNotValidException exception){
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).toList();

        return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                null,errors),HttpStatus.OK);
    }
}
