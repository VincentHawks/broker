package ru.hse.broker.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.hse.broker.models.BankCorp;

import java.util.Optional;

public interface BankCorpRepository extends MongoRepository<BankCorp, String> {

    Optional<BankCorp> findByName(String name);
}
