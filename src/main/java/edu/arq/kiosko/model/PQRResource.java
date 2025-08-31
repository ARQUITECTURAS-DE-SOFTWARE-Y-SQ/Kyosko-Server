package edu.arq.kiosko.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pqrrource")
public class PQRResource {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


}
