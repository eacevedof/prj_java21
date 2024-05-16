package com.eduardoaf.balance.mod_shared.infrastructure.db.query_builders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class InsertQuery {

    private String intoTable = "";
    private String comment = "";
    private final List<String> columns = new ArrayList<>();
    private final List<Object> values = new ArrayList<>();

    public InsertQuery(String table) {
        this.intoTable = table;
    }

    public static InsertQuery getInstance(String intoTable) {
        return new InsertQuery(intoTable);
    }

    public InsertQuery comment(String comment) {
        this.comment = comment;
        return this;
    }

    public InsertQuery addColumn(String column, Object value) {
        columns.add(column);
        values.add(value);
        return this;
    }

    public String getQuery() throws Exception {
        if (
            columns.isEmpty() || values.isEmpty() || columns.size() != values.size()
        ) {
            throw new IllegalArgumentException("Columns and values must not be empty and must have the same size.");
        }

        StringBuilder sqlInsert = new StringBuilder()
                .append("/*").append(comment).append("*/\n")
                .append("INSERT INTO ")
                .append(intoTable).append(" (");

        StringBuilder sqlValues = new StringBuilder("\nVALUES (");

        for (int i = 0; i < columns.size(); i++) {
            sqlInsert.append(columns.get(i));

            var obj = values.get(i);
            String strValue = getObjectAsString(obj);
            if (!strValue.equals("null")) {
                strValue = SanitizeQuery.getInstance().getMysqlString(strValue);
            }
            sqlValues.append(strValue);

            if (i < columns.size() - 1) {
                sqlInsert.append(", ");
                sqlValues.append(", ");
            }
        }
        sqlInsert.append(") ").append(sqlValues).append(")");
        return sqlInsert.toString();
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
