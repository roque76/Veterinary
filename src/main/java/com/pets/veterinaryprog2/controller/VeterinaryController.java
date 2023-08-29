package com.pets.veterinaryprog2.controller;
import com.pets.veterinaryprog2.controller.dto.ResponseDTO;
import com.pets.veterinaryprog2.exceptions.VeterinaryException;
import com.pets.veterinaryprog2.model.*;
import com.pets.veterinaryprog2.service.VeterinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/veterinary")
public class VeterinaryController {
    @Autowired
    private VeterinaryService veterinaryService;

    @GetMapping(path="/cities")
    public ResponseEntity<ResponseDTO> getAllCities(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                veterinaryService.getCities(), null),HttpStatus.OK);

    }
    @GetMapping(path="/cities/id/{id}")
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
    @GetMapping(path="/cities/name/{name}")
    public ResponseEntity<ResponseDTO> getCityByName(@PathVariable String name){
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
    @GetMapping(path="/cities/letter/{letter}")
    public ResponseEntity<ResponseDTO> getCitiesByLetter(@PathVariable String letter){
        try{
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    veterinaryService.findCitiesByLetter(letter),null),HttpStatus.OK);
        }
        catch (VeterinaryException e){
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());

            return  new ResponseEntity<>(new ResponseDTO(HttpStatus.NOT_FOUND.value(),
                    null,errors),HttpStatus.OK);
        }
    }
}
