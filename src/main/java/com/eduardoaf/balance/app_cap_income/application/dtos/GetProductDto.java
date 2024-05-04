package com.eduardoaf.balance.app_cap_income.application.dtos;
import java.util.Map;

public record GetProductDto(
     int id,
     int codeErp,
     String description,
     int idProductBrand,
     String name
) {
    public static GetProductDto fromPrimitives(Map<String, Object> primitives) {
        return new GetProductDto(
            (int) primitives.getOrDefault("id", null),
            (int) primitives.getOrDefault("codeErp", ""),
            (String) primitives.getOrDefault("description", ""),
            (int) primitives.getOrDefault("idProductBrand", null),
            (String) primitives.getOrDefault("name", "")
        );
    }

    public static GetProductDto getInstance(
        int id,
        int codeErp,
        String description,
        int idProductBrand,
        String name
    ) {
        return new GetProductDto(
            id,
            codeErp,
            description,
            idProductBrand,
            name
        );
    }
}
