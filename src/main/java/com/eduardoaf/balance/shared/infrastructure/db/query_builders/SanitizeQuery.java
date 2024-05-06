package com.eduardoaf.balance.shared.infrastructure.db.query_builders;

public final class SanitizeQuery {

    public static SanitizeQuery getInstance() {
        return new SanitizeQuery();
    }

    public String getOnlyValueSanitized(String strValue) {
        strValue = strValue.replace("\\", "\\\\");
        strValue = strValue.replace("'", "\\'");
        return strValue;
    }

    public String getMysqlString(String strValue) {
        strValue = getOnlyValueSanitized(strValue);
        return "'"+strValue+"'";
    }

}
