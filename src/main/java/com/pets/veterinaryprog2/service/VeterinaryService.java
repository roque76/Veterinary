package com.pets.veterinaryprog2.service;

import com.pets.veterinaryprog2.exceptions.VeterinaryException;
import com.pets.veterinaryprog2.model.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class VeterinaryService {
    private List<Vet> vets;
    private List<City> cities;
    private List<Vaccine> vaccines;

    public VeterinaryService(){
        //Simulation of db startup
        cities= new ArrayList<>(); // Initialize list
        //Add cities to the list
        cities.add(new City("16917001","manizales"));
        cities.add(new City("16917063","pereira"));
        cities.add(new City("123","Pasto"));
        cities.add(new City("123","Patos"));
        cities.add(new City("123","Pasta"));
        cities.add(new City("123","Percebes"));
    }
    public City findCItyById(String id) throws VeterinaryException {
        for(City cityFound: this.getCities()){
            if(cityFound.getCode().equals(id)){
                return cityFound;
            }
        }
        throw new VeterinaryException("La ciudad con"+id+"no existe");
    }
    public City findCityByName(String name) throws VeterinaryException{
        for(City cityFound: this.getCities()) {
            if (cityFound.getDescription().equalsIgnoreCase(name)) {
                return cityFound;
            }
        }
        throw new VeterinaryException("La ciudad con" +name+ "no existe");
    }
    public List<City> findCitiesByLetter(String letter) throws VeterinaryException{
        List<City> cities = new ArrayList<>();
        for(City cityFound : this.getCities()){
            if(cityFound.getDescription().toLowerCase().startsWith(letter.toLowerCase())){
                cities.add(cityFound);
            }
        }
        if(cities.isEmpty()){
            throw new VeterinaryException("No hay ciudades que empiezen por "+letter);
        }
        else{
            return cities;
        }
    }
}
