package com.eduardoaf.balance.mod_users.infrastructure.repositories;

import java.util.Map;
import java.util.Collections;

import com.eduardoaf.balance.mod_users.domain.entities.BaseUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.db.query_builders.SanitizeQuery;
import com.eduardoaf.balance.mod_shared.infrastructure.repositories.AbstractMysqlRepository;

@Component
public final class BaseUserReaderRepository extends AbstractMysqlRepository {

    private final Log log;
    private final SanitizeQuery sanitizeQuery;
    private final NumberFormatter numberFormatter;

    @Autowired
    public BaseUserReaderRepository(
            Log log,
            SanitizeQuery sanitizeQuery,
            NumberFormatter numberFormatter
    ) {
        this.log = log;
        this.sanitizeQuery = sanitizeQuery;
        this.numberFormatter = numberFormatter;
    }

    public Map<String, String> getUserByUserIdxxx(int userId) throws Exception {
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

    public BaseUserEntity getUserEntityByUserId(Integer userId) throws Exception {
        String sql = String.format("""
        -- getUserEntityByUserId
        SELECT *
        FROM base_user
        WHERE 1=1
        AND delete_date IS NULL
        AND id = %s
        """, userId);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return null;
        return BaseUserEntity.fromMapRow(
            list.getFirst()
        );
    }

    public BaseUserEntity getUserMinByUserId(
        Integer userId
    ) throws Exception
    {
        String sql = String.format("""
        -- getUserMinByUserId
        SELECT id, is_enabled, delete_date
        FROM base_user
        WHERE 1=1
        AND id = %s
        """, userId);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return null;
        return BaseUserEntity.fromMapRow(
            list.getFirst()
        );
    }

    public Integer doesUserExistByEmail(String email) throws Exception {
        email = sanitizeQuery.getOnlyValueSanitized(email);

        String sql = String.format("""
        -- doesUserExistByEmail
        SELECT id
        FROM base_user
        WHERE 1=1
        AND delete_date IS NULL
        AND email = '%s'
        """, email);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return null;
        return getAsInt(list.getFirst().get("id"));
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

    public Integer getUserIdByEmail(String email) throws Exception {
        email = sanitizeQuery.getOnlyValueSanitized(email);

        String sql = String.format("""
        -- getUserIdByEmail
        SELECT id
        FROM base_user
        WHERE 1=1
        AND delete_date IS NULL
        AND email = '%s'
        """, email);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return null;
        return getAsInt(list.getFirst().get("id"));
    }
}