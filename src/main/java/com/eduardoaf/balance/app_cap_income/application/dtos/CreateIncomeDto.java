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
                codeErp==null ? "" : codeErp.trim(),
                description==null ? "" :description.trim(),
                paymentFor==null ? "" : paymentFor.trim(),
                payedFrom==null ? "" : payedFrom.trim(),
                incomeDate==null ? "" : incomeDate.trim(),
                amount==null ? "" : amount.trim(),
                notes==null ? "" : notes.trim(),
                idOwner==null ? "" : idOwner.trim()
        );
    }

}
