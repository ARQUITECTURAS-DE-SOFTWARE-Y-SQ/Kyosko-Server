package edu.arq.kiosko.model;

import jakarta.persistence.*;

@Entity
@Table(name = "citizen")
public class Citizen {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;
}
