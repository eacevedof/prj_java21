package com.eduardoaf.balance.mod_income.application.dtos;

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

    public static CreatedIncomeDto getInstance(
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
        return new CreatedIncomeDto(
            id,
            uuid,
            codeErp,
            paymentFor,
            payedFrom,
            incomeDate,
            amount,
            notes,
            idOwner
        );
    }
}
