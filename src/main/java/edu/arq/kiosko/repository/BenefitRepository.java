package edu.arq.kiosko.repository;

import edu.arq.kiosko.model.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenefitRepository extends JpaRepository<Benefit, Long> {
    List<Benefit> findByOwnerId(Long ownerId);
}