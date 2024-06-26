package com.eduardoaf.balance.mod_income.application.dtos;

import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;

public record CreateIncomeDto(
        String codeErp,
        String description,
        String paymentFor,
        String payedFrom,
        String incomeDate,
        String amount,
        String notes,
        String idOwner,
        String authUserId,
        String insertPlatform
) {

    public static CreateIncomeDto getInstance(
        String codeErp,
        String description,
        String paymentFor,
        String payedFrom,
        String incomeDate,
        String amount,
        String notes,
        String idOwner,
        String authUserId,
        String insertPlatform
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
            stringFormatter.getTrimOrNull(idOwner),
            stringFormatter.getTrimOrNull(authUserId),
            stringFormatter.getTrimOrNull(insertPlatform)
        );
    }

}
