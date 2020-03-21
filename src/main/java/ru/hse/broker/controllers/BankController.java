package ru.hse.broker.controllers;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hse.broker.models.BankCorp;
import ru.hse.broker.models.BankPriv;
import ru.hse.broker.services.BankCorpService;
import ru.hse.broker.services.BankPrivService;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BankController {

    private final BankCorpService corpService;
    private final BankPrivService privService;

    public BankController(BankCorpService corpService, BankPrivService privService) {
        this.corpService = corpService;
        this.privService = privService;
    }

    @GetMapping("/init")
    public ResponseEntity<String> init() {
        List<BankCorp> banksCorp;
        List<BankPriv> banksPriv;
        try {
            banksCorp = new CsvToBeanBuilder<BankCorp>(new FileReader("src/main/resources/tables/yur_litsa.csv"))
                    .withType(BankCorp.class)
                    .build()
                    .parse();
            corpService.clear();
            corpService.fill(banksCorp);
        } catch (IOException e) {
            System.err.println("File yur_litsa.csv not found");
            return new ResponseEntity<>("Database source unavailable", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            banksPriv = new CsvToBeanBuilder<BankPriv>(new FileReader("src/main/resources/tables/fiz_litsa.csv"))
                    .withType(BankPriv.class)
                    .build()
                    .parse();
            privService.clear();
            privService.fill(banksPriv);
        } catch (IOException e) {
            System.err.println("File yur_litsa.csv not found");
            return new ResponseEntity<>("Database source unavailable", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void pass(){}

    @GetMapping("/list/corporate")
    public ResponseEntity<List<BankCorp>> listCorp() {
        return new ResponseEntity<>(corpService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/private")
    public ResponseEntity<List<BankPriv>> listPriv() {
        return new ResponseEntity<>(privService.getAll(), HttpStatus.OK);
    }
}
