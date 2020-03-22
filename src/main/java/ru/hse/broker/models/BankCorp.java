package ru.hse.broker.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.MongoId;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankCorp {

    @MongoId
    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    private boolean hasInform;

    @CsvBindByPosition(position = 2)
    private int operationalLimit;

    @CsvBindByPosition(position = 3)
    private boolean hasMovementMonitoring;

    @CsvBindByPosition(position = 4)
    private boolean hasBiometricProtection;

    @CsvBindByPosition(position = 5)
    private boolean hasInsurance;

    @CsvBindByPosition(position = 6)
    private boolean hasMobileApp;

    @CsvBindByPosition(position = 7)
    private boolean canDeposit;

    @CsvBindByPosition(position = 8)
    private boolean canCredit;

    @CsvBindByPosition(position = 9)
    private boolean doesBasicPayments;

    @CsvBindByPosition(position = 10)
    private boolean canMakeDocuments;

    @CsvBindByPosition(position = 11)
    private boolean hasOnlineAccounting;

    @CsvBindByPosition(position = 12)
    private boolean canEmitAndControlCorporateCards;

    @CsvBindByPosition(position = 13)
    private String analytics;

    @CsvBindByPosition(position = 14)
    private boolean canGetOperationHistory;

    @CsvBindByPosition(position = 15)
    private boolean hasTemplates;

    @CsvBindByPosition(position = 16)
    private boolean hasAutopay;

    @CsvBindByPosition(position = 17)
    private double encashmentFee;

    @CsvBindByPosition(position = 18)
    private boolean supportsDifferentCurrencies;

    @CsvBindByPosition(position = 19)
    private double acquiringFee;

    @CsvBindByPosition(position = 20)
    private boolean hasOnlineSupport;

    @CsvBindByPosition(position = 21)
    private String monthlyManagerCost;
}
