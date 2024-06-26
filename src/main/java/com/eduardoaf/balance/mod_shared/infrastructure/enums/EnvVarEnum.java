package com.eduardoaf.balance.mod_shared.infrastructure.enums;

public enum EnvVarEnum {

    PATH_HOME("HOME"),
    PATH_PROJECTS("PATHPRJ");

    private final String envKey;

    EnvVarEnum(String envKey) {
        this.envKey = envKey;
    }

    public String getValue() {
        return envKey;
    }
}
