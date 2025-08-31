package edu.arq.kiosko.repository;

import edu.arq.kiosko.model.Citizen;
import edu.arq.kiosko.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ManagerRepository extends JpaRepository<Manager, Long> {
}
