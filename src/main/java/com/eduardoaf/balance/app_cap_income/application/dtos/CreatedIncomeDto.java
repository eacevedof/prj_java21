package com.eduardoaf.balance.app_cap_income.application.dtos;

public record CreatedIncomeDto(
     int id,
     int codeErp,
     String description,
     int idProductBrand,
     String name
) {

    public static CreatedIncomeDto getInstance(
        int id,
        int codeErp,
        String description,
        int idProductBrand,
        String name
    ) {
        return new CreatedIncomeDto(
            id,
            codeErp,
            description,
            idProductBrand,
            name
        );
    }
}
