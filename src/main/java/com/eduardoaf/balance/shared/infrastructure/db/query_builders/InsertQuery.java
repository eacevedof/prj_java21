package com.eduardoaf.balance.shared.infrastructure.db.query_builders;

import java.util.ArrayList;
import java.util.List;

import com.eduardoaf.balance.shared.infrastructure.db.MySql;

public class InsertQuery {

    private MySql mySql = null;
    private String intoTable = "";
    private final List<String> columns = new ArrayList<>();
    private final List<Object> values = new ArrayList<>();

    public InsertQuery(String table) {
        this.intoTable = table;
        //this.mySql = MySql.GetEmailInstanceByEnv();
    }

    public InsertQuery(String table, MySql mySql) {
        this.intoTable = table;
        this.mySql = mySql;
    }

    public static InsertQuery fromIntoTable(String intoTable) {
        return new InsertQuery(intoTable);
    }

    public static InsertQuery fromIntoTable(String intoTable, MySql mySql) {
        return new InsertQuery(intoTable, mySql);
    }

    public InsertQuery addColumn(String column, Object value) {
        columns.add(column);
        values.add(value);
        return this;
    }

    public String getQuery() throws Exception {
        if (columns.isEmpty() || values.isEmpty() || columns.size() != values.size()) {
            throw new IllegalArgumentException("Columns and values must not be empty and must have the same size.");
        }

        StringBuilder sql = new StringBuilder("INSERT INTO ").append(intoTable).append(" (");
        StringBuilder params = new StringBuilder("VALUES (");

        for (int i = 0; i < columns.size(); i++) {
            sql.append(columns.get(i));
            params.append("?");

            if (i < columns.size() - 1) {
                sql.append(", ");
                params.append(", ");
            }
        }

        sql.append(") ").append(params).append(")");
        return sql.toString();
    }
}
