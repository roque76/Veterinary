package com.pets.veterinaryprog2.service;

import com.pets.veterinaryprog2.exceptions.VeterinaryException;
import com.pets.veterinaryprog2.model.Vet;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class VetService {
    private List<Vet> vets;

    public VetService() {
        vets = new ArrayList<>();
        vets.add(new Vet("123","Santiago",(byte)22));
        vets.add(new Vet("345","Mariano",(byte)23));
        vets.add(new Vet("567","Valeria",(byte)22));
        vets.add(new Vet("789","Martina", (byte) 18));
        vets.add(new Vet("101","Martin",(byte)21));
        vets.add(new Vet("123","Valeria",(byte)10));
    }

    public Vet findVetByCode(String code) throws VeterinaryException{
        for(Vet vets: this.getVets()){
            if(vets.getCode().equals(code)){
                return vets;
            }
        }
        throw new VeterinaryException("El veterinario con el codigo"+code+"no existe");
    }

    public List<Vet> findVetsByName(String name) throws VeterinaryException{
        List<Vet> matchedVets = new ArrayList<>();
        for(Vet vets: this.getVets()){
            if(vets.getName().equalsIgnoreCase(name)){
                matchedVets.add(vets);
            }
        }
        if(matchedVets.isEmpty()){
            throw new VeterinaryException("No existen veterinarios de nombre: "+name);
        }
        else{
            return matchedVets;
        }
    }
    public List<Vet> findVetsByFirstLetter(char letter){
        List<Vet> foundVets = new ArrayList<>();
        String letterString = String.valueOf(letter);
        for(Vet vets:this.getVets()){
            if(vets.getName().startsWith(letterString.toUpperCase())){
                foundVets.add(vets);
            }
        }
        return foundVets;
    }

    public Vet getYoungerVet(){
        Vet youngerVet = null;
        byte savedAge = Byte.MAX_VALUE;
        for(Vet vets:this.getVets()){
            if(vets.getAge()<savedAge){
                youngerVet = vets;
                savedAge = vets.getAge();
            }
        }
        return youngerVet;
    }
    public List<Vet> getVetsInRange(byte lowerValue, byte upperValue){
        List<Vet> foundVets = new ArrayList<>();

        for(Vet vets:this.getVets()){
            if(vets.getAge()<=upperValue && vets.getAge()>=lowerValue){
                foundVets.add(vets);
            }
        }
        return foundVets;
    }

}
