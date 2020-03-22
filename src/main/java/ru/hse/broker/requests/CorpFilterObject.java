package ru.hse.broker.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CorpFilterObject {

    private Boolean hasInform;
    private Integer operationalLimit;
    private Boolean hasMovementMonitoring;
    private Boolean hasBiometricProtection;
    private Boolean hasInsurance;
    private Boolean hasMobileApp;
    private Boolean canDeposit;
    private Boolean canCredit;
    private Boolean doesBasicPayments;
    private Boolean canMakeDocuments;
    private Boolean hasOnlineAccounting;
    private Boolean canEmitAndControlCorporateCards;
    private String analytics;
    private Boolean canGetOperationHistory;
    private Boolean hasTemplates;
    private Boolean hasAutopay;
    private Double encashmentFee;
    private Boolean supportsDifferentCurrencies;
    private Double acquiringFee;
    private Boolean hasOnlineSupport;
    private String monthlyManagerCost;

}
