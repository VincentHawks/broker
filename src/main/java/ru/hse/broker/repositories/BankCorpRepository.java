package ru.hse.broker.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.hse.broker.models.BankCorp;

public interface BankCorpRepository extends MongoRepository<BankCorp, String> {
}
