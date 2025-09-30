package edu.arq.kiosko.service;

import edu.arq.kiosko.dto.Citizendto;
import edu.arq.kiosko.model.Citizen;
import edu.arq.kiosko.repository.CitizenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitizenImpl {

    @Autowired private CitizenRepo repository;

    public List<Citizen> getAll(){
        return this.repository.findAll();
    }

    public Optional<Citizen> getById(Long id){
        return this.repository.findById(id);
    }

    public Citizen createBydto(Citizendto dto){
        Citizen current = new Citizen();
        current.setName(dto.getName());
        return this.repository.save(current);
    }

    public Citizen updateByDTO(Citizendto dto){
        Optional<Citizen> existing = this.repository.findById(dto.getId());
        if (existing.isPresent()) {
            Citizen current = existing.get();
            current.setName(dto.getName());
            return this.repository.save(current);
        }
        return null;
    }

    public void deleeByDTO(Citizendto dto){
        this.repository.deleteById(dto.getId());
    }

    public void deleteByid(Long id){
        this.repository.deleteById(id);
    }

    public void deleeAll(){
        this.repository.deleteAll();
    }
}
