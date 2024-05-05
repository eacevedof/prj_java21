package com.eduardoaf.balance.app_cap_income.application.dtos;

public record CreatedIncomeDto(
     int id,
     String codeErp,
     String description,
     String paymentFor,
     String payedFrom,
     String incomeDate,
     Double amount,
     String Notes,
     int idOwner
) {

    public static CreatedIncomeDto getInstance(
        int id,
        String codeErp,
        String description,
        String paymentFor,
        String payedFrom,
        String incomeDate,
        Double amount,
        String Notes,
        int idOwner
    ) {
        return new CreatedIncomeDto(
            id,
            codeErp,
            description,
            paymentFor,
            payedFrom,
            incomeDate,
            amount,
            Notes,
            idOwner
        );
    }
}
