package ru.hse.broker.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrivFilterObject {

    private Boolean hasInform;
    private Integer dailyLimit;
    private Boolean hasMovementMonitoring;
    private Boolean hasBiometricProtection;
    private Boolean hasInsurance;
    private Boolean hasMobileApp;
    private Boolean canDeposit;
    private Boolean canCredit;
    private Boolean doesBasicPayments;
    private Boolean canBindExternalCards;
    private Boolean canGetOperationHistory;
    private Boolean hasAutopay;
    private String concierge;
    private Boolean hasAnalytics;
    private Boolean canTransferToPhone;
    private Boolean hasTemplates;
    private Boolean canOpenMoreCards;
    private Boolean hasOnlineSupport;

}
