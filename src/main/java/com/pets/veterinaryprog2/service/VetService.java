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

    public VetService(List<Vet> vets) {
        vets.add(new Vet("123","Santiago",(byte)22));
        vets.add(new Vet("345","Mariano",(byte)23));
        vets.add(new Vet("567","Valeria",(byte)22));
        vets.add(new Vet("789","Martina", (byte) 18));
        vets.add(new Vet("101","Martin",(byte)21));
    }

    public Vet findVetByCode(String code) throws VeterinaryException{
        for(Vet vets: this.getVets()){
            if(vets.getCode().equals(code)){
                return vets;
            }
        }
        throw new VeterinaryException("El veterinario con el codigo"+code+"no existe");
    }

    public Vet findVetByName(String name) throws VeterinaryException{
        for(Vet vets: this.getVets()){
            if(vets.getName().equalsIgnoreCase(name)){
                return vets;
            }
        }
        throw new VeterinaryException("No exise un veterinario de nombre: "+name);
    }
    public List<Vet> findVetsByFirstLetter(char letter){
        List<Vet> foundVets = new ArrayList<>();
        for(Vet vets:this.getVets()){
            if(vets.getName().toUpperCase().charAt(0)==letter){
                foundVets.add(vets);
            }
        }
        return foundVets;
    }
}
