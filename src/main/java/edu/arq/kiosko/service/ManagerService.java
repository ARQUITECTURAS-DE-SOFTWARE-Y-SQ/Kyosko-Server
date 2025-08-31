package edu.arq.kiosko.service;

import edu.arq.kiosko.model.Manager;
import edu.arq.kiosko.model.PQRResource;
import edu.arq.kiosko.repository.ManagerRepository;
import edu.arq.kiosko.repository.PQRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private ManagerRepository managerrepository;

    public List<Manager> getAll(){
        return this.managerrepository.findAll();
    }


}
