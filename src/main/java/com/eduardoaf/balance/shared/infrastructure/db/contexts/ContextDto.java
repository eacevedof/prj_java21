package com.eduardoaf.balance.shared.infrastructure.db.contexts;

public record ContextDto(
    String id,
    String server,
    String port,
    String database,
    String username,
    String password
) {

    public static ContextDto getInstance(
        String id,
        String server,
        String port,
        String database,
        String username,
        String password
    ) {
        return new ContextDto(
            id,
            server,
            port,
            database,
            username,
            password
        );
    }
}
