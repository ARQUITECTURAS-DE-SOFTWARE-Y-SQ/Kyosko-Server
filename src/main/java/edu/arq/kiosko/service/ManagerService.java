package edu.arq.kiosko.service;

import edu.arq.kiosko.dto.Citizendto;
import edu.arq.kiosko.dto.Managerdto;
import edu.arq.kiosko.dto.PQRdto;
import edu.arq.kiosko.model.Citizen;
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
    public Manager createBydto(Managerdto dto){
        Manager current = new Manager();
        current.setActive(true);
        current.setName(dto.getName());
        current.setCargo(dto.getCargo());
        return this.managerrepository.save(current);
    }

    public Manager updateByDTO(Managerdto dto){
        Manager current = new Manager();
        current.setActive(dto.getActive());
        return this.managerrepository.save(current);
    }

}
