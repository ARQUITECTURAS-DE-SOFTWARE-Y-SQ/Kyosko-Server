package edu.arq.kiosko.service;

import edu.arq.kiosko.model.Citizen;
import edu.arq.kiosko.repository.CitizenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenImpl {

    @Autowired private CitizenRepo repository;

    public List<Citizen> getAll(){
        return this.repository.findAll();
    }
}
