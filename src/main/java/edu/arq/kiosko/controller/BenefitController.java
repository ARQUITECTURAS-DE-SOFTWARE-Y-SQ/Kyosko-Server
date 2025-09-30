package edu.arq.kiosko.controller;

import edu.arq.kiosko.dto.Benefitdto;
import edu.arq.kiosko.model.Benefit;
import edu.arq.kiosko.service.BenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/benefit")
public class BenefitController {

    @Autowired
    private BenefitService benefitService;

    @GetMapping("/all")
    public List<Benefit> showAllBenefits() {
        return this.benefitService.getAll();
    }

    @GetMapping
    public ResponseEntity<Benefitdto> findBenefit(@RequestParam Long id) {
        Benefitdto dto = new Benefitdto("");
        Optional<Benefit> result = this.benefitService.getById(id);

        if (result.isEmpty()) {
            dto.setMessage("No aparece el beneficio con ese ID");
        } else {
            Benefit benefit = result.get();
            dto.setId(benefit.getId());
            dto.setTittle(benefit.getTittle());
            dto.setDescription(benefit.getDescription());
            if (benefit.getOwner() != null) {
                dto.setOwnerId(benefit.getOwner().getId());
            }
            if (benefit.getState() != null) {
                dto.setStateId(benefit.getState().getId());
            }
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/citizen/{citizenId}")
    public List<Benefit> findBenefitsByCitizen(@PathVariable Long citizenId) {
        return this.benefitService.getByCitizenId(citizenId);
    }

    @GetMapping("/pqr/{pqrId}")
    public List<Benefit> findBenefitsByPqr(@PathVariable Long pqrId) {
        return this.benefitService.getBenefitsByPqrId(pqrId);
    }

    @PostMapping("/{benefitId}/pqr/{pqrId}")
    public ResponseEntity<Benefit> addPqrToBenefit(@PathVariable Long benefitId, @PathVariable Long pqrId) {
        Benefit updated = this.benefitService.addPqrToBenefit(benefitId, pqrId);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Benefit> createBenefit(@RequestBody Benefitdto dto) {
        return ResponseEntity.ok(this.benefitService.createBydto(dto));
    }

    @PutMapping
    public ResponseEntity<Benefit> updateBenefit(@RequestBody Benefitdto dto) {
        Benefit updated = this.benefitService.updateByDTO(dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Benefitdto> deleteBenefit(@RequestParam Long id) {
        Benefitdto dto = new Benefitdto("");
        this.benefitService.deleteById(id);
        dto.setMessage("Se eliminó correctamente!!!");
        return ResponseEntity.ok(dto);
    }
}