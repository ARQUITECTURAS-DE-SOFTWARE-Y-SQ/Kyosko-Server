package edu.arq.kiosko.controller;

import edu.arq.kiosko.dto.Managerdto;
import edu.arq.kiosko.dto.PQRdto;
import edu.arq.kiosko.model.Manager;
import edu.arq.kiosko.model.PQRResource;
import edu.arq.kiosko.service.ManagerService;
import edu.arq.kiosko.service.PQRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping("/manager/all")
    public List<Manager> showAllPQRs(){
        return this.managerService.getAll();
    }

    @PostMapping("/manager")
    public ResponseEntity<Manager> createManager(@RequestBody Managerdto manager){
        return ResponseEntity.ok( this.managerService.createBydto(manager));
    }

    @PutMapping("/manager")
    public ResponseEntity<Manager> saveManager(@RequestBody Managerdto manager){
        return ResponseEntity.ok( this.managerService.updateByDTO(manager));
    }
}
