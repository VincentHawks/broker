package ru.hse.broker.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Data
public class BankPriv {

    @Id
    @CsvBindByPosition(position = 0, locale = "ru")
    private String name;

    @CsvBindByPosition(position = 1)
    private boolean doesInform;

    @CsvBindByPosition(position = 2)
    private long dailyLimit;

    @CsvBindByPosition(position = 3)
    private boolean hasMovementMonitoring;

    @CsvBindByPosition(position = 4)
    private boolean hasBiometricProtection;

    @CsvBindByPosition(position = 5)
    private boolean insurance;

    @CsvBindByPosition(position = 6)
    private boolean hasMobileApp;

    @CsvBindByPosition(position = 7)
    private boolean canDeposit;

    @CsvBindByPosition(position = 8)
    private boolean canCredit;

    @CsvBindByPosition(position = 9)
    private boolean doesBasicPayments;

    @CsvBindByPosition(position = 10)
    private boolean canBindExternalCards;

    @CsvBindByPosition(position = 11)
    private boolean canGetOperationHistory;

    @CsvBindByPosition(position = 12)
    private boolean autopay;

    @CsvBindByPosition(position = 13, locale = "ru")
    private String concierge;

    @CsvBindByPosition(position = 14)
    private boolean hasAnalytics;

    @CsvBindByPosition(position = 15)
    private boolean canTransferToPhone;

    @CsvBindByPosition(position = 16)
    private boolean template;

    @CsvBindByPosition(position = 17)
    private boolean canOPenMoreCards;

    @CsvBindByPosition(position = 18)
    private boolean hasOnlineSupport;
}
