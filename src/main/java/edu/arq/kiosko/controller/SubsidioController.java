package edu.arq.kiosko.controller;

import edu.arq.kiosko.dto.Subsidydto;
import edu.arq.kiosko.model.Subsidy;
import edu.arq.kiosko.service.SubsidyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/subsidio")
public class SubsidioController {

    @Autowired
    private SubsidyService subsidyService;

    @GetMapping("/all")
    public List<Subsidy> showAllSubsidies() {
        return this.subsidyService.getAll();
    }

    @GetMapping
    public ResponseEntity<Subsidydto> findSubsidy(@RequestParam Long id) {
        Subsidydto dto = new Subsidydto("");
        Optional<Subsidy> result = this.subsidyService.getById(id);

        if (result.isEmpty()) {
            dto.setMessage("No aparece el subsidio con ese ID");
        } else {
            Subsidy subsidy = result.get();
            dto.setId(subsidy.getId());
            dto.setTitle(subsidy.getTitle());
            dto.setDescription(subsidy.getDescription());
            dto.setAmount(subsidy.getAmount());
            dto.setOwner(subsidy.getOwner());
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/citizen/{citizenId}")
    public List<Subsidy> findSubsidiesByCitizen(@PathVariable Long citizenId) {
        return this.subsidyService.getByCitizenId(citizenId);
    }

    @PostMapping
    public ResponseEntity<Subsidy> createSubsidy(@RequestBody Subsidydto dto) {
        return ResponseEntity.ok(this.subsidyService.createBydto(dto));
    }

    @PutMapping
    public ResponseEntity<Subsidy> updateSubsidy(@RequestBody Subsidydto dto) {
        Subsidy updated = this.subsidyService.updateByDTO(dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Subsidydto> deleteSubsidy(@RequestParam Long id) {
        Subsidydto dto = new Subsidydto("");
        this.subsidyService.deleteById(id);
        dto.setMessage("Se eliminó correctamente!!!");
        return ResponseEntity.ok(dto);
    }
}
