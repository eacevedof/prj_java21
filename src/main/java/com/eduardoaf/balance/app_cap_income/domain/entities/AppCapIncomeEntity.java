package com.eduardoaf.balance.app_cap_income.domain.entities;

import com.eduardoaf.balance.shared.domain.entities.AbstractEntity;
import com.eduardoaf.balance.shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.shared.infrastructure.formatters.StringFormatter;

public final class AppCapIncomeEntity extends AbstractEntity {
    public final Integer id;
    public final String uuid;
    public final String codeErp;
    public final String description;
    public final String paymentFor;
    public final String payedFrom;
    public final String incomeDate;
    public final double amount;
    public final String notes;
    public final Integer idOwner;
    public final String cruCsvNote;
    public final Integer isErpSent;
    public final Integer isEnabled;

    public AppCapIncomeEntity(
        Integer id, 
        String uuid, 
        String codeErp, 
        String description, 
        String paymentFor, 
        String payedFrom,
        String incomeDate, 
        double amount, 
        String notes, 
        Integer idOwner, 
        String cruCsvNote, 
        Integer isErpSent,
        Integer isEnabled
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
        Integer id,
        String uuid,
        String codeErp,
        String description,
        String paymentFor,
        String payedFrom,
        String incomeDate,
        double amount,
        String notes,
        Integer idOwner,
        String cruCsvNote,
        Integer isErpSent,
        Integer isEnabled
    ) {
        return new AppCapIncomeEntity(
            id, 
            uuid,
            codeErp,
            description, 
            paymentFor,
            payedFrom, 
            incomeDate, 
            amount,
            notes, 
            idOwner, 
            cruCsvNote, 
            isErpSent, 
            isEnabled
        );
    }

    public static AppCapIncomeEntity getInstance(
        String codeErp, 
        String description, 
        String paymentFor, 
        String payedFrom,
        String incomeDate, 
        double amount, 
        String notes, 
        Integer idOwner
    ) {
        NumberFormatter numberFormatter = NumberFormatter.getInstance();
        StringFormatter stringFormatter = StringFormatter.getInstance();
        return new AppCapIncomeEntity(
            numberFormatter.getNull(), 
            stringFormatter.getNull(), 
            codeErp, 
            description, 
            paymentFor, 
            payedFrom, 
            incomeDate, 
            amount,
            notes, 
            idOwner, 
            stringFormatter.getNull(), 
            0, 
            1
        );
    }
}
