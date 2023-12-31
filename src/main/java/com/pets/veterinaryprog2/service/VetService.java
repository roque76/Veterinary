package com.pets.veterinaryprog2.service;

import com.pets.veterinaryprog2.controller.dto.RangeDataStructureDTO;
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

    public String createVet(Vet vet) throws VeterinaryException{
        if(this.verifyVetExist(vet)){
            throw new VeterinaryException("El codigo ingresado ya existe: ");
        }
        else {
            this.vets.add(vet);
            return "Veterinario agregado correctamente";
        }
    }

    public String updateVet(String code, Vet updVet)throws VeterinaryException{
        for(Vet vets:this.getVets()){
            if(vets.getCode().equals(code)){
                vets.setAge(updVet.getAge());
                vets.setName(updVet.getName());
                return "Datos actualizados con exito";
            }
        }
        throw new VeterinaryException("El codigo ingresado no existe");
    }

    public List<RangeDataStructureDTO> getVetsInDefaultRange(){
        byte totalFirstRange = 0;
        byte totalSecondRange = 0;
        byte totalThirdRange= 0;
        byte totalFourthRange= 0;

        for(Vet vets:this.getVets()){
            if(vets.getAge()<=10 && vets.getAge()>=1){
                totalFirstRange ++;
            } else if (vets.getAge()>=11 && vets.getAge()<=20) {
                totalSecondRange ++;
            } else if (vets.getAge()>=21 && vets.getAge()<=30) {
                totalThirdRange ++;
            } else if (vets.getAge()>=31) {
                totalFourthRange ++;
            }
        }
        List<RangeDataStructureDTO> rangeList = new ArrayList<>();

        rangeList.add(new RangeDataStructureDTO("Range: 1-10",totalFirstRange));
        rangeList.add(new RangeDataStructureDTO("Range: 11-20",totalSecondRange));
        rangeList.add(new RangeDataStructureDTO("Range: 21-30",totalThirdRange));
        rangeList.add(new RangeDataStructureDTO("Range: 31-Infinite",totalFourthRange));

        return rangeList;
    }

    public List<Vet> getIntercalatedVets(String message){
        List<Vet> pairVets = new ArrayList<>();
        List<Vet> impairVets = new ArrayList<>();
        List<Vet> finalVets = new ArrayList<>();

        for(Vet vets:this.getVets()){
            if(vets.getAge()%2==0){
                pairVets.add(vets);
            }
            else{
                impairVets.add(vets);
            }
        }

        while(!pairVets.isEmpty()|| !impairVets.isEmpty()){
            if("pair".equalsIgnoreCase(message)){
                if(!pairVets.isEmpty()){
                    finalVets.add(pairVets.get(0));
                    pairVets.remove(0);
                }
                if (!impairVets.isEmpty()) {
                    finalVets.add(impairVets.get(0));
                    impairVets.remove(0);
                }
            }
            if("impair".equalsIgnoreCase(message)){
                if(!impairVets.isEmpty()){
                    finalVets.add(impairVets.get(0));
                    impairVets.remove(0);
                }
                if(!pairVets.isEmpty()){
                    finalVets.add(pairVets.get(0));
                    pairVets.remove(0);
                }
            }
        }

        return finalVets;
    }

    private boolean verifyVetExist(Vet vet)
{
        for(Vet vets:this.getVets()){
            if(vet.getCode().equals(vets.getCode())){
                return true;
            }
        }
        return false;
    }

}
