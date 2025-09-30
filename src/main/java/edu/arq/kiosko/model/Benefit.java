package edu.arq.kiosko.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity
@Table(name = "benefit")
public class Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tittle", columnDefinition = "text")
    private String tittle;

    @Column(name = "name", columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Citizen owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private GenericStatus state;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="benefit_pqrs",
            joinColumns = @JoinColumn(name="benefit_id"),
            inverseJoinColumns = @JoinColumn(name="pqr_id")
    )
    private List<PQRResource> pqrs;

    public Citizen getOwner() {
        return owner;
    }

    public void setOwner(Citizen owner) {
        this.owner = owner;
    }

    public GenericStatus getState() {
        return state;
    }

    public void setState(GenericStatus state) {
        this.state = state;
    }

    public List<PQRResource> getPqrs() {
        return pqrs;
    }

    public void setPqrs(List<PQRResource> pqrs) {
        this.pqrs = pqrs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
