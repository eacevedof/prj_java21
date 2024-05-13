package com.eduardoaf.balance.app_cap_income.application.dtos;

import com.eduardoaf.balance.shared.infrastructure.formatters.StringFormatter;

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
        StringFormatter stringFormatter = StringFormatter.getInstance();
        return new CreateIncomeDto(
            stringFormatter.getTrimOrNull(codeErp),
            stringFormatter.getTrimOrNull(description),
            stringFormatter.getTrimOrNull(paymentFor),
            stringFormatter.getTrimOrNull(payedFrom),
            stringFormatter.getTrimOrNull(incomeDate),
            stringFormatter.getTrimOrNull(amount),
            stringFormatter.getTrimOrNull(notes),
            stringFormatter.getTrimOrNull(idOwner)
        );
    }

}
