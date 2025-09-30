package edu.arq.kiosko.dto;

import edu.arq.kiosko.model.Subsidy;

public class Subsidydto extends Subsidy {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Subsidydto(String message) {
        this.message = message;
    }
}