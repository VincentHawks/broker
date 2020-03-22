package ru.hse.broker.services;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.hse.broker.exceptions.ResourceNotFoundException;
import ru.hse.broker.models.BankPriv;
import ru.hse.broker.repositories.BankPrivRepository;

import java.util.List;

@Service
public class BankPrivService {

    private final BankPrivRepository repository;

    public BankPrivService(BankPrivRepository repository) {
        this.repository = repository;
    }

    public List<BankPriv> getAll() {
        return repository.findAll();
    }

    public BankPriv getByName(String name) throws ResourceNotFoundException {
        return repository.findByName(name).orElseThrow(ResourceNotFoundException::new);
    }

    public BankPriv saveAndFlush(@NonNull BankPriv entry) {
        repository.save(entry);
        return entry;
    }

    public void fill(@NonNull List<BankPriv> bankPrivList) {
        repository.saveAll(bankPrivList);
    }

    public void clear() {
        repository.deleteAll();
    }
}
