package com.eduardoaf.balance.app_cap_income.domain.entities;

import com.eduardoaf.balance.shared.domain.entities.AbstractEntity;
import java.util.Date;

public final class AppCapExpenseEntity extends AbstractEntity {
    public final int id;
    public final String uuid;
    public final String codeErp;
    public final String description;
    public final String paymentFor;
    public final String payedTo;
    public final Date expenseDate;
    public final double amount;
    public final String notes;
    public final int idOwner;
    public final String cruCsvNote;
    public final int isErpSent;
    public final int isEnabled;

    public AppCapExpenseEntity(
        int id, String uuid, String codeErp, String description, String paymentFor, String payedTo,
        Date expenseDate, double amount, String notes, int idOwner, String cruCsvNote, int isErpSent,
        int isEnabled
    ) {
        this.id = id;
        this.uuid = uuid;
        this.codeErp = codeErp;
        this.description = description;
        this.paymentFor = paymentFor;
        this.payedTo = payedTo;
        this.expenseDate = expenseDate;
        this.amount = amount;
        this.notes = notes;
        this.idOwner = idOwner;
        this.cruCsvNote = cruCsvNote;
        this.isErpSent = isErpSent;
        this.isEnabled = isEnabled;
    }

    public static AppCapExpenseEntity getInstance(
        int id, String uuid, String codeErp, String description, String paymentFor, String payedTo,
        Date expenseDate, double amount, String notes, int idOwner, String cruCsvNote, int isErpSent,
        int isEnabled
    ) {
        return new AppCapExpenseEntity(
            id, uuid,codeErp, description, paymentFor, payedTo, expenseDate, amount,
            notes, idOwner, cruCsvNote, isErpSent, isEnabled
        );
    }
}
