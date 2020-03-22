package ru.hse.broker.services;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.hse.broker.exceptions.ResourceNotFoundException;
import ru.hse.broker.models.BankCorp;
import ru.hse.broker.repositories.BankCorpRepository;

import java.util.List;

@Service
public class BankCorpService {

    private final BankCorpRepository repository;

    public BankCorpService(BankCorpRepository repository) {
        this.repository = repository;
    }

    public List<BankCorp> getAll() {
        return repository.findAll();
    }

    public BankCorp getByName(String name) throws ResourceNotFoundException{
        return repository.findByName(name).orElseThrow(ResourceNotFoundException::new);
    }

    public BankCorp saveAndFlush(@NonNull BankCorp entry) {
        repository.save(entry);
        return entry;
    }

    public void fill(@NonNull List<BankCorp> bankCorpList) {
        repository.saveAll(bankCorpList);
    }

    public void clear() {
        repository.deleteAll();
    }
}
