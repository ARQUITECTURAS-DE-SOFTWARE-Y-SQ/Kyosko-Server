package edu.arq.kiosko.controller;

import edu.arq.kiosko.model.Citizen;
import edu.arq.kiosko.service.CitizenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/beneficio")
public class BeneficioController {
    @Autowired private CitizenImpl serviceCitizen;

    @GetMapping("/citizen")
    public List<Citizen> showCitizen(){
        return this.serviceCitizen.getAll();
    }
}
