package edu.arq.kiosko.repository;

import edu.arq.kiosko.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepo extends JpaRepository<Citizen, Long> {
}
