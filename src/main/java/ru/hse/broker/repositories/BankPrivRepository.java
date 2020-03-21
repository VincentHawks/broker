package ru.hse.broker.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.hse.broker.models.BankPriv;

import java.util.List;
import java.util.Optional;

public interface BankPrivRepository extends MongoRepository<BankPriv, String> {

    Optional<BankPriv> findByName(String name);
}
