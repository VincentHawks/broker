package ru.hse.broker.services;

import ru.hse.broker.repositories.BankPrivRepository;

public class BankPrivService {

    private final BankPrivRepository repository;

    public BankPrivService(BankPrivRepository repository) {
        this.repository = repository;
    }
}
