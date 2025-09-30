package edu.arq.kiosko.repository;

import edu.arq.kiosko.model.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenefitPQRRepository extends JpaRepository<Benefit, Long> {
    @Query("SELECT b FROM Benefit b JOIN b.pqrs p WHERE p.id = :pqrId")
    List<Benefit> findBenefitsByPqrId(Long pqrId);
}