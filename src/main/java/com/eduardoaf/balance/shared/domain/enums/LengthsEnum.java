package com.eduardoaf.balance.shared.domain.enums;

public enum LengthsEnum {
    UUID(50),
    CODE_ERP(25),
    DESCRIPTION(25);

    private final int length;

    LengthsEnum(int length) {
        this.length = length;
    }

    public int value() {
        return this.length;
    }
}
