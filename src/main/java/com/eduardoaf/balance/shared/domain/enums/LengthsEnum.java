package com.eduardoaf.balance.shared.domain.enums;

public enum LengthsEnum {
    ADDRESS(250),
    AMOUNT(10),
    BIRTHDATE(10),
    CODE_ERP(25),
    DESCRIPTION(150),
    EMAIL(100),
    FULLNAME(100),
    INCOME_DATE(10),
    NOTES(500),
    PAYED_FROM(250),
    PAYMENT_FOR(250),
    PHONE(20),
    SECRET(100),
    URL_PICTURE(300),
    UUID(50);

    private final int length;

    LengthsEnum(int length) {
        this.length = length;
    }

    public int value() {
        return this.length;
    }
}
