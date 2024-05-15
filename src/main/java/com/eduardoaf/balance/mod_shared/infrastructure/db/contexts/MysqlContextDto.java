package com.eduardoaf.balance.mod_shared.infrastructure.db.contexts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record MysqlContextDto(
    String id,
    String server,
    String port,
    String database,
    String username,
    String password
) {

    public static MysqlContextDto getInstance(
        String id,
        String server,
        String port,
        String database,
        String username,
        String password
    ) {
        return new MysqlContextDto(
            id,
            server,
            port,
            database,
            username,
            password
        );
    }

    public static MysqlContextDto getInstance(
            String connectionString,
            String username,
            String password
    ) {

        return new MysqlContextDto(
                "123",
                getServerNameFromConnectionString(connectionString),
                getPortFromConnectionString(connectionString),
                getDatabaseFromConnectionString(connectionString),
                username,
                password
        );
    }

    private static String getServerNameFromConnectionString(String connectionString) {
        Pattern pattern = Pattern.compile("jdbc:mysql://(.*?):");
        Matcher matcher = pattern.matcher(connectionString);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String getPortFromConnectionString(String connectionString) {
        Pattern pattern = Pattern.compile("jdbc:mysql://.*?:(\\d+)/");
        Matcher matcher = pattern.matcher(connectionString);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "-1";
    }

    private static String getDatabaseFromConnectionString(String connectionString) {
        Pattern pattern = Pattern.compile("jdbc:mysql://.*?/([^?]+)");
        Matcher matcher = pattern.matcher(connectionString);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
