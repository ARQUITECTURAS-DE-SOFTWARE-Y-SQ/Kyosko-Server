package edu.arq.kiosko.repository;

import edu.arq.kiosko.model.GenericStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenericStatusRepository extends JpaRepository<GenericStatus, Long> {
    List<GenericStatus> findByDiscriminator(String discriminator);
}
