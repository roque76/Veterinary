package com.pets.veterinaryprog2.service;

import com.pets.veterinaryprog2.exceptions.VeterinaryException;
import com.pets.veterinaryprog2.model.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CityService {
    private List<City> cities;

    public CityService(){
        //Simulation of db startup
        cities= new ArrayList<>(); // Initialize list
        //Add cities to the list
        cities.add(new City("16917001","manizales"));
        cities.add(new City("16917063","pereira"));
        cities.add(new City("123","pasto"));
    }
    public City findCItyById(String id) throws VeterinaryException {
        //Iterate over the saved cities
        for(City cityFound: this.getCities()){
            //If the code of the saved object matches the id return the object
            if(cityFound.getCode().equals(id)){
                return cityFound;
            }
        }//Create a error message
        throw new VeterinaryException("La ciudad con"+id+"no existe");
    }
    public City findCityByName(String name) throws VeterinaryException{
        //Iterate over the saved cities
        for(City cityFound: this.getCities()) {
            // If the city matches ignoring the case return the whole object
            if (cityFound.getDescription().equalsIgnoreCase(name)) {
                return cityFound;
            }
        }
        // Create error message
        throw new VeterinaryException("La ciudad con" +name+ "no existe");
    }
    public List<City> findCitiesByFirstLetter(char letter) {
        //Create an empty list to store the city objects that matches
        List<City> citiesFound = new ArrayList<>();
        String letterString = String.valueOf(letter);
        //Iterate over the saved cities
        for(City cities : this.getCities()) {//save the first char of the description and compare it
            if (cities.getDescription().startsWith(letterString.toLowerCase())) {
                citiesFound.add(cities);
            }
        }//Return cities
        return citiesFound;
    }
}
