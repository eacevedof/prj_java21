package com.eduardoaf.balance.shared.infrastructure.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eduardoaf.balance.shared.infrastructure.db.contexts.ContextDto;

public final class MySql {

    private final ContextDto contextDto;
    private String stringConnection = null;
    private Connection connection = null;
    private int lastInsertId = -1;
    private int rowsAffected = -1;

    public MySql(ContextDto contextDto) {
        this.contextDto = contextDto;
    }

    public List<Map<String, String>> query(String query) throws Exception {
        query = query.trim();
        List<Map<String, String>> rowsResult = new ArrayList<>();
        if (query.isEmpty()) return rowsResult;

        loadStringConnectionOrFail();
        loadConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        connection.close();

        List<String> columnNames = getColumnNames(resultSet);
        while (resultSet.next()) {
            Map<String, String> row = new HashMap<>();
            for (String columnName : columnNames) {
                String value = resultSet.getString(columnName);
                row.put(columnName, resultSet.wasNull() ? null : value);
            }
            rowsResult.add(row);
        }
        return rowsResult;
    }

    public boolean execute(String query) throws Exception {
        query = query.trim();
        if (query.isEmpty()) return false;

        boolean isInsert = query.contains("INSERT INTO ");
        if (isInsert) query += "; SELECT LAST_INSERT_ID();";

        loadStringConnectionOrFail();
        loadConnection();

        Statement statement = connection.createStatement();

        if (isInsert) {
            statement.execute(query);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    lastInsertId = resultSet.getInt(1);
                }
            }
        } else {
            rowsAffected = statement.executeUpdate(query);
        }
        return true;
    }

    private List<String> getColumnNames(ResultSet resultSet) throws SQLException {
        List<String> columnNames = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnName(i));
        }
        return columnNames;
    }

    private void loadStringConnectionOrFail() throws Exception {
        if (!stringConnection.isEmpty()) {
            return;
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        stringConnection = String.format(
            "jdbc:mysql://%s:%s/%s",
            this.contextDto.server(),
            this.contextDto.port(),
            this.contextDto.database()
        );
    }

    private void loadConnection() throws Exception {
        if (connection == null) {
            connection = DriverManager.getConnection(
                    stringConnection,
                    contextDto.username(),
                    contextDto.password()
            );
            return;
        }
        if (connection.isClosed()) {
            connection = DriverManager.getConnection(
                    stringConnection,
                    contextDto.username(),
                    contextDto.password()
            );
        }
    }

    public int getRowsAffected() {
        return rowsAffected;
    }

    public int getLastInsertId() {
        return lastInsertId;
    }

}
