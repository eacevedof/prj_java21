package com.eduardoaf.balance.sys_users.infrastructure.repositories;

import java.util.Map;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardoaf.balance.shared.infrastructure.file.Log;
import com.eduardoaf.balance.shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.shared.infrastructure.db.query_builders.SanitizeQuery;
import com.eduardoaf.balance.shared.infrastructure.repositories.AbstractMysqlRepository;

@Component
public final class SysUserReaderRepository extends AbstractMysqlRepository {

    private final Log log;
    private final SanitizeQuery sanitizeQuery;
    private final NumberFormatter numberFormatter;

    @Autowired
    public SysUserReaderRepository (
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
        FROM sys_users
        WHERE 1=1
        AND delete_date IS NULL
        AND id = %s
        """, userId);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return Collections.emptyMap();
        return list.getFirst();
    }

    public int doesUserExistByEmail(String email) throws Exception {
        email = sanitizeQuery.getOnlyValueSanitized(email);

        String sql = String.format("""
        -- doesUserExistByEmail
        SELECT id
        FROM sys_users
        WHERE 1=1
        AND delete_date IS NULL
        AND email = '%s'
        """, email);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return 0;
        return getAsInt(list.getFirst().get("id"));
    }

    public String getUuidByUserId(int userId) throws Exception {
        String sql = String.format("""
        -- getUuidByUserId
        SELECT uuid
        FROM sys_users
        WHERE 1=1
        AND delete_date IS NULL
        AND id = %s
        """, userId);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return "";
        return list.getFirst().get("uuid");
    }

}