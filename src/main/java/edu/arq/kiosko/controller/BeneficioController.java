package edu.arq.kiosko.controller;

import edu.arq.kiosko.dto.Citizendto;
import edu.arq.kiosko.model.Citizen;
import edu.arq.kiosko.service.CitizenImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beneficio")
public class BeneficioController {
    @Autowired private CitizenImpl serviceCitizen;

    @GetMapping("/citizen/all")
    public List<Citizen> showAllCitizen(){
        return this.serviceCitizen.getAll();
    }

    @GetMapping("/citizen")
    public ResponseEntity<Citizendto> findCitizen(@RequestParam Long id){
        Citizendto dto = new Citizendto("");
        Optional<Citizen> result = this.serviceCitizen.getById(id);
        if(result.isEmpty()){
            dto.setMessage("No aparece el ciudadano con ese ID");
        }else {
            dto.setId(result.get().getId()) ;
            dto.setName(result.get().getName());
        }
        return ResponseEntity.ok(dto);

    }

    @PostMapping("/citizen")
    public ResponseEntity<Citizen> createCitizen(@RequestBody Citizendto dto){
        return ResponseEntity.ok( this.serviceCitizen.createBydto(dto));
    }

    @PutMapping("/citizen")
    public ResponseEntity<Citizen> saveCitizen(@RequestBody Citizendto dto){
        return ResponseEntity.ok( this.serviceCitizen.updateByDTO(dto));
    }


    @DeleteMapping("/citizen")
    public ResponseEntity<Citizendto> dellCitizen(@RequestParam Long id){
        Citizendto dto = new Citizendto("");
        this.serviceCitizen.deleteByid(id);
        dto.setMessage("Se elimino correctamente!!!");
        return ResponseEntity.ok(dto );
    }
}
