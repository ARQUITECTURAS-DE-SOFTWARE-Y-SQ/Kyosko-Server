package edu.arq.kiosko.controller;

import edu.arq.kiosko.dto.GenericStatusdto;
import edu.arq.kiosko.model.GenericStatus;
import edu.arq.kiosko.service.GenericStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/status")
public class GenericStatusController {

    @Autowired
    private GenericStatusService statusService;

    @GetMapping("/all")
    public List<GenericStatus> showAllStatus() {
        return this.statusService.getAll();
    }

    @GetMapping
    public ResponseEntity<GenericStatusdto> findStatus(@RequestParam Long id) {
        GenericStatusdto dto = new GenericStatusdto("");
        Optional<GenericStatus> result = this.statusService.getById(id);

        if (result.isEmpty()) {
            dto.setMessage("No aparece el estado con ese ID");
        } else {
            GenericStatus status = result.get();
            dto.setId(status.getId());
            dto.setName(status.getName());
            dto.setDescription(status.getDescription());
            dto.setDiscriminator(status.getDiscriminator());
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/discriminator/{discriminator}")
    public List<GenericStatus> findStatusByDiscriminator(@PathVariable String discriminator) {
        return this.statusService.getByDiscriminator(discriminator);
    }

    @PostMapping
    public ResponseEntity<GenericStatus> createStatus(@RequestBody GenericStatusdto dto) {
        return ResponseEntity.ok(this.statusService.createBydto(dto));
    }

    @PutMapping
    public ResponseEntity<GenericStatus> updateStatus(@RequestBody GenericStatusdto dto) {
        GenericStatus updated = this.statusService.updateByDTO(dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<GenericStatusdto> deleteStatus(@RequestParam Long id) {
        GenericStatusdto dto = new GenericStatusdto("");
        this.statusService.deleteById(id);
        dto.setMessage("Se eliminó correctamente!!!");
        return ResponseEntity.ok(dto);
    }
}