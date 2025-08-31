package edu.arq.kiosko.service;

import edu.arq.kiosko.dto.Citizendto;
import edu.arq.kiosko.dto.PQRdto;
import edu.arq.kiosko.model.Citizen;
import edu.arq.kiosko.model.Manager;
import edu.arq.kiosko.model.PQRResource;
import edu.arq.kiosko.repository.ManagerRepository;
import edu.arq.kiosko.repository.PQRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PQRService {
    @Autowired
    private PQRRepository pqrrepository;
    @Autowired
    private ManagerService managerservice;

    public List<PQRResource> getAll(){
        return this.pqrrepository.findAll();
    }

    public PQRResource createBydto(PQRdto dto){
        PQRResource current = new PQRResource();
        current.setDescription(dto.getDescription());
        //todo:pendiente validar que el estado existe en GenericStatus
        current.setState(dto.getState());
        current.setHandler(getHandlerFree());
        current.setDescription(dto.getDescription());
        return this.pqrrepository.save(current);
    }

    public PQRResource updateByDTO(PQRdto dto){
        PQRResource current = new PQRResource();
        current.setState(dto.getState());
        return this.pqrrepository.save(current);
    }


    private Manager getHandlerFree(){
        Random random = new Random();
        List<Manager> listManagers = this.managerservice.getAll().stream()
                .filter(Manager::getActive)
                .collect(Collectors.toList());

        return listManagers.get(random.nextInt(listManagers.size()) );
    }

}
