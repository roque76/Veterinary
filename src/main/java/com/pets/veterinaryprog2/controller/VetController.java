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

    @GetMapping (path="/by_code/{code}")
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
    @GetMapping(path="/by_name/{name}")
    public ResponseEntity<ResponseDTO> getVetByName(@PathVariable String name){
        if(name != null && !name.isEmpty()){
            try {
                return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                        veterinaryService.findVetByName(name), null), HttpStatus.OK);
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

    @GetMapping(path="/by_firstletter/{letter}")
    public ResponseEntity<ResponseDTO> getVetsByFirstLetter(@PathVariable char letter){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                veterinaryService.findVetsByFirstLetter(letter),null),HttpStatus.OK);
    }



}
