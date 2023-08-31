package com.pets.veterinaryprog2.controller;
import com.pets.veterinaryprog2.controller.dto.ResponseDTO;
import com.pets.veterinaryprog2.exceptions.VeterinaryException;
import com.pets.veterinaryprog2.service.CityService;
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
@RequestMapping(path="/cities")
public class CityController {
    @Autowired
    private CityService veterinaryService;

    @GetMapping(path="/get_cities")
    public ResponseEntity<ResponseDTO> getAllCities(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                veterinaryService.getCities(), null),HttpStatus.OK);

    }
    @GetMapping(path="/by_id/{id}")
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
    @GetMapping(path="by_name/{name}")
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
    @GetMapping(path="by_letter/{letter}")
    public ResponseEntity<ResponseDTO> getCitiesByFirstLetter(@PathVariable char letter){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    veterinaryService.findCitiesByFirstLetter(letter),null),HttpStatus.OK);

    }
}
