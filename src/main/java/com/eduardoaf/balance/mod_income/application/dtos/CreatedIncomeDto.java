package com.eduardoaf.balance.mod_income.application.dtos;

import com.eduardoaf.balance.mod_income.domain.entities.AppCapIncomeEntity;

public record CreatedIncomeDto(
     Integer id,
     String uuid,
     String codeErp,
     String paymentFor,
     String payedFrom,
     String incomeDate,
     Double amount,
     String notes,
     Integer idOwner
) {

    public static CreatedIncomeDto fromIncomeEntity(
        AppCapIncomeEntity incomeEntity
    ) {
        return new CreatedIncomeDto(
            incomeEntity.getId(),
            incomeEntity.getUuid(),
            incomeEntity.getCodeErp(),
            incomeEntity.getPaymentFor(),
            incomeEntity.getPayedFrom(),
            incomeEntity.getIncomeDate(),
            incomeEntity.getAmount(),
            incomeEntity.getNotes(),
            incomeEntity.getIdOwner()
        );
    }
}
