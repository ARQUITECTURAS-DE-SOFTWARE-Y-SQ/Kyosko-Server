package edu.arq.kiosko.service;

import edu.arq.kiosko.dto.GenericStatusdto;
import edu.arq.kiosko.model.GenericStatus;
import edu.arq.kiosko.repository.GenericStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenericStatusService {

    @Autowired
    private GenericStatusRepository repository;

    public List<GenericStatus> getAll() {
        return this.repository.findAll();
    }

    public Optional<GenericStatus> getById(Long id) {
        return this.repository.findById(id);
    }

    public List<GenericStatus> getByDiscriminator(String discriminator) {
        return this.repository.findByDiscriminator(discriminator);
    }

    public GenericStatus createBydto(GenericStatusdto dto) {
        GenericStatus current = new GenericStatus();
        current.setName(dto.getName());
        current.setDescription(dto.getDescription());
        current.setDiscriminator(dto.getDiscriminator());
        return this.repository.save(current);
    }

    public GenericStatus updateByDTO(GenericStatusdto dto) {
        Optional<GenericStatus> existing = this.repository.findById(dto.getId());
        if (existing.isPresent()) {
            GenericStatus current = existing.get();
            current.setName(dto.getName());
            current.setDescription(dto.getDescription());
            current.setDiscriminator(dto.getDiscriminator());
            return this.repository.save(current);
        }
        return null;
    }

    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
