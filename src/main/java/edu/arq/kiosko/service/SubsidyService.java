package edu.arq.kiosko.service;

import edu.arq.kiosko.dto.Subsidydto;
import edu.arq.kiosko.model.Citizen;
import edu.arq.kiosko.model.Subsidy;
import edu.arq.kiosko.repository.CitizenRepo;
import edu.arq.kiosko.repository.SubsidyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubsidyService {

    @Autowired
    private SubsidyRepository repository;

    @Autowired
    private CitizenRepo citizenRepo;

    public List<Subsidy> getAll() {
        return this.repository.findAll();
    }

    public Optional<Subsidy> getById(Long id) {
        return this.repository.findById(id);
    }

    public List<Subsidy> getByCitizenId(Long citizenId) {
        return this.repository.findByOwnerId(citizenId);
    }

    public Subsidy createBydto(Subsidydto dto) {
        Subsidy current = new Subsidy();
        current.setTitle(dto.getTitle());
        current.setDescription(dto.getDescription());
        current.setAmount(dto.getAmount());

        if (dto.getOwner() != null && dto.getOwner().getId() != null) {
            Optional<Citizen> citizen = citizenRepo.findById(dto.getOwner().getId());
            citizen.ifPresent(current::setOwner);
        }

        return this.repository.save(current);
    }

    public Subsidy updateByDTO(Subsidydto dto) {
        Optional<Subsidy> existing = this.repository.findById(dto.getId());
        if (existing.isPresent()) {
            Subsidy current = existing.get();
            current.setTitle(dto.getTitle());
            current.setDescription(dto.getDescription());
            current.setAmount(dto.getAmount());

            if (dto.getOwner() != null && dto.getOwner().getId() != null) {
                Optional<Citizen> citizen = citizenRepo.findById(dto.getOwner().getId());
                citizen.ifPresent(current::setOwner);
            }

            return this.repository.save(current);
        }
        return null;
    }

    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}