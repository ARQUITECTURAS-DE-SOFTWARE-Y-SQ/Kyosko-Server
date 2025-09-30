package edu.arq.kiosko.controller;

import edu.arq.kiosko.dto.Citizendto;
import edu.arq.kiosko.model.Citizen;
import edu.arq.kiosko.service.CitizenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/citizen")
public class CitizenController {

    @Autowired
    private CitizenImpl citizenService;

    @GetMapping("/all")
    public List<Citizen> showAllCitizens() {
        return this.citizenService.getAll();
    }

    @GetMapping
    public ResponseEntity<Citizen> findCitizen(@RequestParam Long id) {
        Optional<Citizen> result = this.citizenService.getById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Citizen> createCitizen(@RequestBody Citizendto dto) {
        return ResponseEntity.ok(this.citizenService.createBydto(dto));
    }

    @PutMapping
    public ResponseEntity<Citizen> updateCitizen(@RequestBody Citizendto dto) {
        Citizen updated = this.citizenService.updateByDTO(dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCitizen(@RequestParam Long id) {
        this.citizenService.deleteByid(id);
        return ResponseEntity.ok().build();
    }
}