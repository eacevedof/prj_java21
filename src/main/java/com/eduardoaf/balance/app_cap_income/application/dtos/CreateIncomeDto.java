package com.eduardoaf.balance.app_cap_income.application.dtos;

public record CreateIncomeDto(
        String codeErp,
        String description,
        String paymentFor,
        String payedFrom,
        String incomeDate,
        String amount,
        String notes,
        String idOwner
) {

    public static CreateIncomeDto getInstance(
            String codeErp,
            String description,
            String paymentFor,
            String payedFrom,
            String incomeDate,
            String amount,
            String notes,
            String idOwner
    ) {
        return new CreateIncomeDto(
                codeErp,
                description,
                paymentFor,
                payedFrom,
                incomeDate,
                amount,
                notes,
                idOwner
        );
    }

}
