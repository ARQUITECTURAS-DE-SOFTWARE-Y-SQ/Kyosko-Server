package edu.arq.kiosko.service;

import edu.arq.kiosko.dto.Managerdto;
import edu.arq.kiosko.dto.PqrsDtoPeticion;
import edu.arq.kiosko.model.GenericStatus;
import edu.arq.kiosko.model.Manager;
import edu.arq.kiosko.model.PQRResource;
import edu.arq.kiosko.repository.GenericStatusRepository;
import edu.arq.kiosko.repository.ManagerRepository;
import edu.arq.kiosko.repository.PQRRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PQRService {
    private static final Logger log = LoggerFactory.getLogger(PQRService.class);
    @Autowired
    private PQRRepository pqrrepository;
    @Autowired
    private ManagerService managerservice;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private GenericStatusRepository statusRepository;

    public List<PQRResource> getAll(){
        return this.pqrrepository.findAll();
    }

    public PQRResource createBydto(PqrsDtoPeticion dto){
        PQRResource current = new PQRResource();
        current.setDescription(dto.getDescription());
        current.setHandler(getHandlerFree());
        return this.pqrrepository.save(current);
    }

    public PQRResource updateByDTO(PqrsDtoPeticion dto){
        Optional<PQRResource> existing = this.pqrrepository.findById(dto.getId().longValue());
        if (existing.isEmpty()) {
            return null;
        }

        PQRResource current = existing.get();
        current.setDescription(dto.getDescription());

        if (dto.getHandlerId() != null) {
            Optional<Manager> manager = managerRepository.findById(dto.getHandlerId());
            manager.ifPresent(current::setHandler);
        }

        if (dto.getStateId() != null) {
            Optional<GenericStatus> status = statusRepository.findById(dto.getStateId());
            status.ifPresent(current::setState);
        }

        return this.pqrrepository.save(current);
    }


    private Manager getHandlerFree(){
        Random random = new Random();
        List<Manager> listManagers = this.managerservice.getAll().stream()
                .filter(Manager::getActive)
                .toList();
        if(listManagers.isEmpty()){
            return generarManagerPorDefecto();
        }
        return listManagers.get(random.nextInt(listManagers.size()) );
    }

    private Manager generarManagerPorDefecto(){
        Managerdto managerdto = new Managerdto();
        managerdto.setActive(true);
        managerdto.setName("Manager por defecto");
        managerdto.setCargo("Admin");
        return  this.managerservice.createBydto(managerdto);
    }


}
