package edu.arq.kiosko.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subsidio")
public class SubsidioController {

    @GetMapping
    public String message(){
        return "Hola Mundo";
    }
}
