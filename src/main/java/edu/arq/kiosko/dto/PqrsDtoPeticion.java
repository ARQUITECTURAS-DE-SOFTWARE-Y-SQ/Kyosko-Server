package edu.arq.kiosko.dto;

import edu.arq.kiosko.model.PQRResource;

public class PqrsDtoPeticion  {
    private Integer id;
    private String description;
    private Long handlerId;
    private Long stateId;
    private Integer idBeneficio;
    private Integer idUsuario;

    // Constructor vacío
    public PqrsDtoPeticion() {
    }

    // Constructor con parámetros
    public PqrsDtoPeticion(Integer id, String description, Long handlerId, Long stateId, Integer idBeneficio, Integer idUsuario) {
        this.id = id;
        this.description = description;
        this.handlerId = handlerId;
        this.stateId = stateId;
        this.idBeneficio = idBeneficio;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Integer getIdBeneficio() {
        return idBeneficio;
    }

    public void setIdBeneficio(Integer idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
