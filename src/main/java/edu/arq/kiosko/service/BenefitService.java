package edu.arq.kiosko.service;

import edu.arq.kiosko.dto.Benefitdto;
import edu.arq.kiosko.model.Benefit;
import edu.arq.kiosko.model.Citizen;
import edu.arq.kiosko.model.PQRResource;
import edu.arq.kiosko.repository.BenefitPQRRepository;
import edu.arq.kiosko.repository.BenefitRepository;
import edu.arq.kiosko.repository.CitizenRepo;
import edu.arq.kiosko.repository.PQRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenefitService {

    @Autowired
    private BenefitRepository repository;

    @Autowired
    private CitizenRepo citizenRepo;

    @Autowired
    private BenefitPQRRepository benefitPQRRepository;

    @Autowired
    private PQRRepository pqrRepository;

    public List<Benefit> getAll() {
        return this.repository.findAll();
    }

    public Optional<Benefit> getById(Long id) {
        return this.repository.findById(id);
    }

    public List<Benefit> getByCitizenId(Long citizenId) {
        return this.repository.findByOwnerId(citizenId);
    }

    public List<Benefit> getBenefitsByPqrId(Long pqrId) {
        return this.benefitPQRRepository.findBenefitsByPqrId(pqrId);
    }

    public Benefit addPqrToBenefit(Long benefitId, Long pqrId) {
        Optional<Benefit> benefitOpt = this.repository.findById(benefitId);
        Optional<PQRResource> pqrOpt = this.pqrRepository.findById(pqrId);

        if (benefitOpt.isPresent() && pqrOpt.isPresent()) {
            Benefit benefit = benefitOpt.get();
            PQRResource pqr = pqrOpt.get();

            if (benefit.getPqrs() != null && !benefit.getPqrs().contains(pqr)) {
                benefit.getPqrs().add(pqr);
                return this.repository.save(benefit);
            }
        }
        return null;
    }

    public Benefit createBydto(Benefitdto dto) {
        Benefit current = new Benefit();
        current.setTittle(dto.getTittle());
        current.setDescription(dto.getDescription());

        if (dto.getOwnerId() != null) {
            Optional<Citizen> citizen = citizenRepo.findById(dto.getOwnerId());
            citizen.ifPresent(current::setOwner);
        }

        return this.repository.save(current);
    }

    public Benefit updateByDTO(Benefitdto dto) {
        Optional<Benefit> existing = this.repository.findById(dto.getId());
        if (existing.isPresent()) {
            Benefit current = existing.get();
            current.setTittle(dto.getTittle());
            current.setDescription(dto.getDescription());

            if (dto.getOwnerId() != null) {
                Optional<Citizen> citizen = citizenRepo.findById(dto.getOwnerId());
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