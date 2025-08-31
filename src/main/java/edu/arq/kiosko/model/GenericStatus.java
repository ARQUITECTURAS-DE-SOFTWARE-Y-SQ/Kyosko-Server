package edu.arq.kiosko.model;

import jakarta.persistence.*;

@Entity
@Table(name = "generic_state")
public class GenericStatus {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "descriminator", columnDefinition = "text")
    private String descriminator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriminator() {
        return descriminator;
    }

    public void setDescriminator(String descriminator) {
        this.descriminator = descriminator;
    }
}
