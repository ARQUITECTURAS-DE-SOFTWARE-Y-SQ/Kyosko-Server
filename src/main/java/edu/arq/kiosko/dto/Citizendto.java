package edu.arq.kiosko.dto;

import edu.arq.kiosko.model.Citizen;
import edu.arq.kiosko.model.PQRResource;

public class Citizendto extends Citizen {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Citizendto(String message) {
        this.message = message;
    }
}

