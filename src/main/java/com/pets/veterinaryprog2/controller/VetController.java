package com.pets.veterinaryprog2.controller;

import com.pets.veterinaryprog2.controller.dto.ResponseDTO;
import com.pets.veterinaryprog2.exceptions.VeterinaryException;
import com.pets.veterinaryprog2.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/vets")
public class VetController {
    @Autowired
    private VetService veterinaryService;

    @GetMapping (path="/byCode/{code}")
    public ResponseEntity<ResponseDTO> getVetById(@PathVariable String code){
        if(code != null && !code.isEmpty()) {
            try {
                return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                        veterinaryService.findVetByCode(code), null), HttpStatus.OK);
            } catch (VeterinaryException e) {
                List<String> error = new ArrayList<>();
                error.add(e.getMessage());

                return new ResponseEntity<>(new ResponseDTO(HttpStatus.NOT_FOUND.value(),
                        null, error), HttpStatus.OK);
            }
        }
        else{
            List<String> error = new ArrayList<>();
            error.add("El codigo es obligatorio");

            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,error),HttpStatus.OK);
        }
    }
    @GetMapping(path="/byName/{name}")
    public ResponseEntity<ResponseDTO> getVetsByName(@PathVariable String name){
        if(name != null && !name.isEmpty()){
            try {
                return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                        veterinaryService.findVetsByName(name), null), HttpStatus.OK);
            } catch (VeterinaryException e) {
                List<String> error = new ArrayList<>();
                error.add(e.getMessage());

                return new ResponseEntity<>(new ResponseDTO(HttpStatus.NOT_FOUND.value(),
                        null, error), HttpStatus.OK);
            }
        }
        else{
            List<String> error = new ArrayList<>();
            error.add("El nombre es obligatorio");

            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,error),HttpStatus.OK);
        }
    }

    @GetMapping(path="/byLetter/{letter}")
    public ResponseEntity<ResponseDTO> getVetsByFirstLetter(@PathVariable char letter){
        if(Character.isLetter(letter)) {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    veterinaryService.findVetsByFirstLetter(letter), null), HttpStatus.OK);
        }
        else{
            List<String> error = new ArrayList<>();
            error.add("El caracter proveido debe ser una letra");

            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,error),HttpStatus.OK);
        }
    }

    @GetMapping(path="/Vets")
    public ResponseEntity<ResponseDTO> getVets(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                veterinaryService.getVets(),null),HttpStatus.OK);
    }

    @GetMapping(path="/youngerVet")
    public ResponseEntity<ResponseDTO> getYoungerVet(){
        return  new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                 veterinaryService.getYoungerVet(),null),HttpStatus.OK);
    }

    @GetMapping(path="/getVetsInRange/{lowerValue}/{upperValue}")
    public ResponseEntity<ResponseDTO> getVetsInRange(@PathVariable byte lowerValue, @PathVariable byte upperValue){
        if(upperValue<=lowerValue){
            List<String> error = new ArrayList<>();
            error.add("El valor de la edad inferior no debe superar o ser igual al superior.");

            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,error),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    veterinaryService.getVetsInRange(lowerValue,upperValue),null),HttpStatus.OK);
        }
    }
}
