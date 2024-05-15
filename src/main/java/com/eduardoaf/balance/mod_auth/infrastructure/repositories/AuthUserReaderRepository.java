package com.eduardoaf.balance.mod_auth.infrastructure.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.eduardoaf.balance.mod_shared.infrastructure.db.query_builders.SanitizeQuery;
import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.repositories.AbstractMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class AuthUserReaderRepository extends AbstractMysqlRepository {

    private final Log log;
    private final SanitizeQuery sanitizeQuery;
    private final NumberFormatter numberFormatter;

    @Autowired
    public AuthUserReaderRepository(
            Log log,
            SanitizeQuery sanitizeQuery,
            NumberFormatter numberFormatter
    ) {
        this.log = log;
        this.sanitizeQuery = sanitizeQuery;
        this.numberFormatter = numberFormatter;
    }

    public Map<String, String> getUserByUserId(int userId) throws Exception {
        String sql = String.format("""
        -- getUserByUserId
        SELECT *
        FROM base_user
        WHERE 1=1
        AND delete_date IS NULL
        AND id = %s
        """, userId);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return Collections.emptyMap();
        return list.getFirst();
    }

    public String getUuidByUserId(int userId) throws Exception {
        String sql = String.format("""
        -- getUuidByUserId
        SELECT uuid
        FROM base_user
        WHERE 1=1
        AND delete_date IS NULL
        AND id = %s
        """, userId);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return "";
        return list.getFirst().get("uuid");
    }

    public List<Map<String, String>> getUsersCredentialsByEmail(String email) throws Exception {
        email = sanitizeQuery.getOnlyValueSanitized(email);

        String sql = String.format("""
        -- getUserIdByEmail
        SELECT id, email, secret, delete_date, is_enabled
        FROM base_user
        WHERE 1=1
        AND delete_date IS NULL
        AND email = '%s'
        LIMIT 2
        """, email);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty())
            return Collections.emptyList();
        return list;
    }
}