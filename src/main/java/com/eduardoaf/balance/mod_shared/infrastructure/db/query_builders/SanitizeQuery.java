package com.eduardoaf.balance.mod_shared.infrastructure.db.query_builders;

import org.springframework.stereotype.Component;

@Component
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
