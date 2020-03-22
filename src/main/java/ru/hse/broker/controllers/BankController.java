package ru.hse.broker.controllers;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hse.broker.exceptions.ResourceNotFoundException;
import ru.hse.broker.models.BankCorp;
import ru.hse.broker.models.BankPriv;
import ru.hse.broker.requests.CorpFilterObject;
import ru.hse.broker.requests.PrivFilterObject;
import ru.hse.broker.services.BankCorpService;
import ru.hse.broker.services.BankPrivService;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        return new ResponseEntity<>("Database wiped and reinitialised successfully",
                HttpStatus.OK);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void pass(){}

    @GetMapping("/corporate")
    public ResponseEntity<List<BankCorp>> listCorp() {
        return new ResponseEntity<>(corpService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/private")
    public ResponseEntity<List<BankPriv>> listPriv() {
        return new ResponseEntity<>(privService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/corporate/{name}")
    public ResponseEntity<BankCorp> getCorp(@PathVariable String name) {
        try {
            return new ResponseEntity<>(corpService.getByName(name), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/private/{name}")
    public ResponseEntity<BankPriv> getPriv(@PathVariable String name) {
        try {
            return new ResponseEntity<>(privService.getByName(name), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/corporate/select",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BankCorp>> applyFiltersToCorp
            (@RequestBody CorpFilterObject filter) {
        List<BankCorp> filteredList = corpService.getAll().stream()
                /*
                They *shouldn't* be null, but they *may* be.
                */
                .filter(bank -> {
                    if (filter.getHasInform() == null) return true;
                    else return filter.getHasInform() && bank.isHasInform();
                })
                .filter(bank -> {
                    if (filter.getOperationalLimit() == null) return true;
                    else return filter.getOperationalLimit() >= bank.getOperationalLimit();
                })
                .filter(bank -> {
                    if (filter.getHasMovementMonitoring() == null) return true;
                    else return filter.getHasMovementMonitoring() && bank.isHasMovementMonitoring();
                })
                .filter(bank -> {
                    if(filter.getHasBiometricProtection() == null) return true;
                    else return filter.getHasBiometricProtection() && bank.isHasBiometricProtection();
                })
                .filter(bank -> {
                    if(filter.getHasInsurance() == null) return true;
                    else return filter.getHasInsurance() && bank.isHasInsurance();
                })
                .filter(bank -> {
                    if(filter.getHasMobileApp() == null) return true;
                    else return filter.getHasMobileApp() && bank.isHasMobileApp();
                })
                .filter(bank -> {
                    if(filter.getCanDeposit() == null) return true;
                    else return filter.getCanDeposit() && bank.isCanDeposit();
                })
                .filter(bank -> {
                    if(filter.getCanCredit() == null) return true;
                    else return filter.getCanCredit() && bank.isCanCredit();
                })
                .filter(bank -> {
                    if(filter.getDoesBasicPayments() == null) return true;
                    else return filter.getDoesBasicPayments() && bank.isDoesBasicPayments();
                })
                .filter(bank -> {
                    if(filter.getCanMakeDocuments() == null) return true;
                    else return filter.getCanMakeDocuments() && bank.isCanMakeDocuments();
                })
                .filter(bank -> {
                    if(filter.getHasOnlineAccounting() == null) return true;
                    else return filter.getHasOnlineAccounting() && bank.isHasOnlineAccounting();
                })
                .filter(bank -> {
                    if(filter.getCanEmitAndControlCorporateCards() == null) return true;
                    else return filter.getCanEmitAndControlCorporateCards() && bank.isCanEmitAndControlCorporateCards();
                })
                .filter(bank -> {
                    if(filter.getAnalytics() == null) return true;
                    else {
                        try {
                            int filterAnalytics = Integer.parseInt(filter.getAnalytics());
                            int bankAnalytics = Integer.parseInt(bank.getAnalytics());
                            return filterAnalytics <= bankAnalytics;
                        } catch (NumberFormatException e) {
                            return true;
                        }
                    }
                })
                .filter(bank -> {
                    if(filter.getCanGetOperationHistory() == null) return true;
                    else return filter.getCanGetOperationHistory() && bank.isCanGetOperationHistory();
                })
                .filter(bank -> {
                    if(filter.getHasTemplates() == null) return true;
                    else return filter.getHasTemplates() && bank.isHasTemplates();
                })
                .filter(bank -> {
                    if(filter.getHasAutopay() == null) return true;
                    else return filter.getHasAutopay() && bank.isHasAutopay();
                })
                .filter(bank -> {
                    if(filter.getEncashmentFee() == null) return true;
                    else return filter.getEncashmentFee() <= bank.getEncashmentFee();
                })
                .filter(bank -> {
                    if(filter.getSupportsDifferentCurrencies() == null) return true;
                    else return filter.getSupportsDifferentCurrencies() && bank.isSupportsDifferentCurrencies();
                })
                .filter(bank -> {
                    if(filter.getAcquiringFee() == null) return true;
                    else return filter.getAcquiringFee() <= bank.getAcquiringFee();
                })
                .filter(bank -> {
                    if(filter.getHasOnlineSupport() == null) return true;
                    else return filter.getHasOnlineSupport() && bank.isHasOnlineSupport();
                })
                .filter(bank -> {
                    if(filter.getMonthlyManagerCost() == null) return true;
                    else {
                        try {
                            int filterManagerCost = Integer.parseInt(filter.getMonthlyManagerCost());
                            int bankManagerCost = Integer.parseInt(bank.getMonthlyManagerCost());
                            return filterManagerCost <= bankManagerCost;
                        } catch (NumberFormatException e) {
                            return true;
                        }
                    }
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }

    @PostMapping(value = "/private/select",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BankPriv>> applyFiltersToPriv
            (@RequestBody PrivFilterObject filter) {
        @SuppressWarnings("Duplicated code fragment")
        List<BankPriv> filteredList = privService.getAll().stream()
                .filter(bank -> {
                    if (filter.getHasInform() == null) return true;
                    else return filter.getHasInform() && bank.isHasInform();
                })
                .filter(bank -> {
                    if (filter.getDailyLimit() == null) return true;
                    else return filter.getDailyLimit() >= bank.getDailyLimit();
                })
                .filter(bank -> {
                    if (filter.getHasMovementMonitoring() == null) return true;
                    else return filter.getHasMovementMonitoring() && bank.isHasMovementMonitoring();
                })
                .filter(bank -> {
                    if(filter.getHasBiometricProtection() == null) return true;
                    else return filter.getHasBiometricProtection() && bank.isHasBiometricProtection();
                })
                .filter(bank -> {
                    if(filter.getHasInsurance() == null) return true;
                    else return filter.getHasInsurance() && bank.isHasInsurance();
                })
                .filter(bank -> {
                    if(filter.getHasMobileApp() == null) return true;
                    else return filter.getHasMobileApp() && bank.isHasMobileApp();
                })
                .filter(bank -> {
                    if(filter.getCanDeposit() == null) return true;
                    else return filter.getCanDeposit() && bank.isCanDeposit();
                })
                .filter(bank -> {
                    if(filter.getCanCredit() == null) return true;
                    else return filter.getCanCredit() && bank.isCanCredit();
                })
                .filter(bank -> {
                    if(filter.getDoesBasicPayments() == null) return true;
                    else return filter.getDoesBasicPayments() && bank.isDoesBasicPayments();
                })
                .filter(bank -> {
                    if(filter.getCanBindExternalCards() == null) return true;
                    else return filter.getCanBindExternalCards() && bank.isCanBindExternalCards();
                })
                .filter(bank -> {
                    if(filter.getCanGetOperationHistory() == null) return true;
                    else return filter.getCanGetOperationHistory() && bank.isCanGetOperationHistory();
                })
                .filter(bank -> {
                    if(filter.getHasAutopay() == null) return true;
                    else return filter.getHasAutopay() && bank.isHasAutopay();
                })
                .filter(bank -> {
                    if(filter.getConcierge() == null) return true;
                    else {
                        try {
                            int filterConsierge = Integer.parseInt(filter.getConcierge());
                            int bankConsierge = Integer.parseInt(bank.getConcierge());
                            return filterConsierge <= bankConsierge;
                        } catch (NumberFormatException e) {
                            return true;
                        }
                    }
                })
                .filter(bank -> {
                    if(filter.getHasAnalytics() == null) return true;
                    else return filter.getHasAnalytics() && bank.isHasAnalytics();
                })
                .filter(bank -> {
                    if(filter.getCanTransferToPhone() == null) return true;
                    else return filter.getCanTransferToPhone() && bank.isCanTransferToPhone();
                })
                .filter(bank -> {
                    if(filter.getHasTemplates() == null) return true;
                    else return filter.getHasTemplates() && bank.isHasTemplates();
                })
                .filter(bank -> {
                    if(filter.getCanOpenMoreCards() == null) return true;
                    else return filter.getCanOpenMoreCards() && bank.isCanOpenMoreCards();
                })
                .filter(bank -> {
                    if(filter.getHasOnlineSupport() == null) return true;
                    else return filter.getHasOnlineSupport() && bank.isHasOnlineSupport();
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }
}
