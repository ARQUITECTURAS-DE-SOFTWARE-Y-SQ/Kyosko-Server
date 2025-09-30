package edu.arq.kiosko.repository;

import edu.arq.kiosko.model.Subsidy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubsidyRepository extends JpaRepository<Subsidy, Long> {
    List<Subsidy> findByOwnerId(Long ownerId);
}