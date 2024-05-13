package com.eduardoaf.balance.app_cap_income.application.dtos;

public record CreatedIncomeDto(
     Integer id,
     String uuid,
     String codeErp,
     String description,
     String paymentFor,
     String payedFrom,
     String incomeDate,
     Double amount,
     String notes,
     Integer idOwner
) {

    public static CreatedIncomeDto getInstance(
        Integer id,
        String uuid,
        String codeErp,
        String description,
        String paymentFor,
        String payedFrom,
        String incomeDate,
        Double amount,
        String notes,
        Integer idOwner
    ) {
        return new CreatedIncomeDto(
            id,
            uuid.trim(),
            codeErp.trim(),
            description.trim(),
            paymentFor.trim(),
            payedFrom.trim(),
            incomeDate.trim(),
            amount,
            notes.trim(),
            idOwner
        );
    }
}
