package com.eduardoaf.balance.mod_shared.infrastructure.db.query_builders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class UpdateQuery {

    private String updateTable = "";
    private String comment = "";
    private final List<String> columns = new ArrayList<>();
    private final List<Object> values = new ArrayList<>();
    private final List<String> whereClause = new ArrayList<>();

    public UpdateQuery(String table) {
        this.updateTable = table;
    }

    public static UpdateQuery getInstance(String updateTable) {
        return new UpdateQuery(updateTable);
    }

    public UpdateQuery addColumn(String column, Object value) {
        columns.add(column);
        values.add(value);
        return this;
    }

    public UpdateQuery where(String andCondition) {
        whereClause.add(andCondition);
        return this;
    }

    public UpdateQuery comment(String comment) {
        this.comment = comment;
        return this;
    }

    public UpdateQuery where(String strField, Object anyValue) {
        String strValue = getObjectAsString(anyValue);
        strValue = SanitizeQuery.getInstance().getMysqlString(strValue);
        String andCondition = strField + " = " + strValue;
        whereClause.add(andCondition);
        return this;
    }

    public String getQuery() throws Exception {
        if (columns.isEmpty() || values.isEmpty() || columns.size() != values.size()) {
            throw new IllegalArgumentException("Columns and values must not be empty and must have the same size.");
        }

        StringBuilder sqlUpdate = new StringBuilder()
                .append("/*").append(comment).append("*/")
                .append("UPDATE ")
                .append(updateTable)
                .append(" SET ");

        for (int i = 0; i < columns.size(); i++) {
            sqlUpdate.append(columns.get(i)).append(" = ");

            var obj = values.get(i);
            String strValue = getObjectAsString(obj);
            if (!strValue.equals("null")) {
                strValue = SanitizeQuery.getInstance().getMysqlString(strValue);
            }
            sqlUpdate.append(strValue);

            if (i < columns.size() - 1) {
                sqlUpdate.append(", ");
            }
        }

        sqlUpdate.append(" WHERE 1=1");
        if (!whereClause.isEmpty()) {
            sqlUpdate.append(" AND ").append(String.join(" AND ", whereClause));
        }

        return sqlUpdate.toString();
    }

    private String getObjectAsString(Object obj) {
        if (obj == null)
            return "null";

        if (obj instanceof Double || obj instanceof Integer)
            return String.valueOf(obj);

        if (obj instanceof Date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format((Date) obj);
        }
        return obj.toString();
    }
}