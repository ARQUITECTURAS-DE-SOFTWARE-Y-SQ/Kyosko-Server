package edu.arq.kiosko.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pqrrource")
public class PQRResource {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "handler_id", referencedColumnName = "id")
    private Manager handler;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private GenericStatus state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Manager getHandler() {
        return handler;
    }

    public void setHandler(Manager handler) {
        this.handler = handler;
    }

    public GenericStatus getState() {
        return state;
    }

    public void setState(GenericStatus state) {
        this.state = state;
    }
}
