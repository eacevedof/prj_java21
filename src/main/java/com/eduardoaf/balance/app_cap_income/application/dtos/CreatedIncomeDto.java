package com.eduardoaf.balance.app_cap_income.application.dtos;

public record CreatedIncomeDto(
     int id,
     String uuid,
     String codeErp,
     String description,
     String paymentFor,
     String payedFrom,
     String incomeDate,
     Double amount,
     String notes,
     int idOwner
) {

    public static CreatedIncomeDto getInstance(
        int id,
        String uuid,
        String codeErp,
        String description,
        String paymentFor,
        String payedFrom,
        String incomeDate,
        Double amount,
        String notes,
        int idOwner
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
