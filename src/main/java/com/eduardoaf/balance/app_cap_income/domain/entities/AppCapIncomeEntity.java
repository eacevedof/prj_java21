package com.eduardoaf.balance.app_cap_income.domain.entities;

import com.eduardoaf.balance.shared.domain.entities.AbstractEntity;

import java.util.Date;

public final class AppCapIncomeEntity extends AbstractEntity {

    public final int id;
    public final String uuid;
    public final String codeErp;
    public final String description;
    public final String paymentFor;
    public final String payedFrom;
    public final String incomeDate;
    public final double amount;
    public final String notes;
    public final int idOwner;
    public final String cruCsvNote;
    public final int isErpSent;
    public final int isEnabled;

    public AppCapIncomeEntity(
        int id, String uuid, String codeErp, String description, String paymentFor, String payedFrom,
        String incomeDate, double amount, String notes, int idOwner, String cruCsvNote, int isErpSent,
        int isEnabled
    ) {
        this.id = id;
        this.uuid = uuid;
        this.codeErp = codeErp;
        this.description = description;
        this.paymentFor = paymentFor;
        this.payedFrom = payedFrom;
        this.incomeDate = incomeDate;
        this.amount = amount;
        this.notes = notes;
        this.idOwner = idOwner;
        this.cruCsvNote = cruCsvNote;
        this.isErpSent = isErpSent;
        this.isEnabled = isEnabled;
    }

    public static AppCapIncomeEntity getInstance(
        int id, String uuid, String codeErp, String description, String paymentFor, String payedFrom,
        String incomeDate, double amount, String notes, int idOwner, String cruCsvNote, int isErpSent,
        int isEnabled
    ) {
        return new AppCapIncomeEntity(
            id, uuid,codeErp, description, paymentFor, payedFrom, incomeDate, amount,
            notes, idOwner, cruCsvNote, isErpSent, isEnabled
        );
    }

    public static AppCapIncomeEntity getInstance(
            String codeErp, String description, String paymentFor, String payedFrom,
            String incomeDate, double amount, String notes, int idOwner
    ) {
        return new AppCapIncomeEntity(
                0, null, codeErp, description, paymentFor, payedFrom, incomeDate, amount,
                notes, idOwner, null, 0, 1
        );
    }
}
