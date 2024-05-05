package com.eduardoaf.balance.app_cap_income.application.dtos;

public record CreateIncomeDto(
     int id,
     int codeErp,
     String description,
     int idProductBrand,
     String name
) {

    public static CreateIncomeDto getInstance(
        int id,
        int codeErp,
        String description,
        int idProductBrand,
        String name
    ) {
        return new CreateIncomeDto(
            id,
            codeErp,
            description,
            idProductBrand,
            name
        );
    }
}
