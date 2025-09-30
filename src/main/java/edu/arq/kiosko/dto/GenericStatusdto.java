package edu.arq.kiosko.dto;

import edu.arq.kiosko.model.GenericStatus;

public class GenericStatusdto extends GenericStatus {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GenericStatusdto(String message) {
        this.message = message;
    }
}