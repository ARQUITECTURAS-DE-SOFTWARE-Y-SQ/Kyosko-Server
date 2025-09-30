package edu.arq.kiosko.controller;

import edu.arq.kiosko.dto.PqrsDtoPeticion;
import edu.arq.kiosko.model.PQRResource;
import edu.arq.kiosko.service.PQRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/peticionesQuejasReclamos")
public class PQRController {
    @Autowired
    private PQRService servicePQR;

    @GetMapping("/pqr/all")
    public List<PQRResource> showAllPQRs(){
        return this.servicePQR.getAll();
    }

    @PostMapping("/pqr")
    public ResponseEntity<PQRResource> createPQR(@RequestBody PqrsDtoPeticion pqr){
        return ResponseEntity.ok(this.servicePQR.createBydto(pqr));
    }

    @PutMapping("/pqr")
    public ResponseEntity<PQRResource> savePQR(@RequestBody PqrsDtoPeticion pqr){
        return ResponseEntity.ok( this.servicePQR.updateByDTO(pqr));
    }
}
