package com.eduardoaf.balance.mod_income.domain.entities;

import com.eduardoaf.balance.mod_shared.domain.entities.AbstractEntity;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;
import lombok.Getter;

import java.util.Map;


@Getter
public final class AppCapIncomeEntity extends AbstractEntity {

    private final String uuid;
    private final String codeErp;
    private final String description;
    private final String paymentFor;
    private final String payedFrom;
    private final String incomeDate;
    private final double amount;
    private final String notes;
    private final Integer idOwner;

    private AppCapIncomeEntity(
        Integer id, 
        String uuid, 
        String codeErp, 
        String description, 
        String paymentFor, 
        String payedFrom,
        String incomeDate, 
        double amount, 
        String notes,
        Integer idOwner
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
    }

    public static AppCapIncomeEntity fromMapRow(Map<String, String> mapRow) {
        StringFormatter stringFormatter = new StringFormatter();
        NumberFormatter numberFormatter = new NumberFormatter();
        
        AppCapIncomeEntity capIncomeEntity = new AppCapIncomeEntity(
            numberFormatter.getIntegerOrNull(mapRow.get("id")),
            stringFormatter.getTrimOrNull(mapRow.get("uuid")),
            stringFormatter.getTrimOrNull(mapRow.get("code_erp")),
            stringFormatter.getTrimOrNull(mapRow.get("description")),
            stringFormatter.getTrimOrNull(mapRow.get("payment_for")),
            stringFormatter.getTrimOrNull(mapRow.get("payed_from")),
            stringFormatter.getTrimOrNull(mapRow.get("income_date")),
            numberFormatter.getDoubleOrNull(mapRow.get("amount")),
            stringFormatter.getTrimOrNull(mapRow.get("notes")),
            numberFormatter.getIntegerOrNull(mapRow.get("id_owner"))
        );
        loadParentByMapRow(capIncomeEntity, mapRow);
        return capIncomeEntity;
    }

    public static AppCapIncomeEntity fromStrings(
        String codeErp,
        String description,
        String paymentFor,
        String payedFrom,
        String incomeDate,
        String amount,
        String notes,
        String idOwner
    ) {
        StringFormatter stringFormatter = new StringFormatter();
        NumberFormatter numberFormatter = new NumberFormatter();
        return new AppCapIncomeEntity(
            numberFormatter.getNull(),
            stringFormatter.getNull(),
            stringFormatter.getTrimOrNull(codeErp),
            stringFormatter.getTrimOrNull(description),
            stringFormatter.getTrimOrNull(paymentFor),
            stringFormatter.getTrimOrNull(payedFrom),
            stringFormatter.getTrimOrNull(incomeDate),
            numberFormatter.getDouble3dec(amount),
            stringFormatter.getTrimOrNull(notes),
            numberFormatter.getIntegerOrNull(idOwner)
        );
    }
}
