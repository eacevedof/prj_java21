package com.eduardoaf.balance.shared.domain.enums;

public enum LengthsEnum {
    UUID(50),
    CODE_ERP(25),
    PAYMENT_FOR(150),
    PAYED_FROM(250),
    INCOME_DATE(10),
    AMOUNT(10),
    DESCRIPTION(150);

    private final int length;

    LengthsEnum(int length) {
        this.length = length;
    }

    public int value() {
        return this.length;
    }
}
