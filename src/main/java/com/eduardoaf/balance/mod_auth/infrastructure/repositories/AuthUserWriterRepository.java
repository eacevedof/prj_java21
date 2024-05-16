package com.eduardoaf.balance.mod_auth.infrastructure.repositories;

import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardoaf.balance.mod_shared.infrastructure.db.query_builders.UpdateQuery;
import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.DateFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.UuidFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.repositories.AbstractMysqlRepository;

import com.eduardoaf.balance.mod_auth.domain.entities.AuthUserEntity;

@Component
public final class AuthUserWriterRepository extends AbstractMysqlRepository {

    private final Log log;
    private final DateFormatter dateFormatter;
    private final StringFormatter stringFormatter;

    @Autowired
    public AuthUserWriterRepository(
            Log log,
            DateFormatter dateFormatter,
            StringFormatter stringFormatter
    ) {
        this.log = log;
        this.dateFormatter = dateFormatter;
        this.stringFormatter = stringFormatter;
    }

    public void updateUserLogged(AuthUserEntity authUserEntity) throws Exception {
        var sql = UpdateQuery.getInstance("base_user")
                .comment("updateUserLogged")
                .addColumn("update_platform", authUserEntity.getInsertPlatform())
                .addColumn("update_user", authUserEntity.getInsertUser())
                .addColumn("update_date", dateFormatter.getNow())
                .addColumn("log_attempts", 0)
                .where("id", stringFormatter.getAlwaysString(authUserEntity.getI()))
                .getQuery();
        log.debug(sql, "updateUserLogged");
        this.execute(sql);
    }

    public void updateLogAttemptByEmail(AuthUserEntity authUserEntity) throws Exception {
        Integer totalAttempts = getTotalAttemptsByEmail(
            authUserEntity.getEmail()
        );
        var sql = UpdateQuery.getInstance("base_user")
                .comment("updateLogAttemptByEmail")
                .addColumn("update_platform", authUserEntity.getInsertPlatform())
                .addColumn("update_user", authUserEntity.getInsertUser())
                .addColumn("update_date", dateFormatter.getNow())
                .addColumn("log_attempts", totalAttempts + 1)
                .where("email", authUserEntity.getEmail())
                .getQuery();
        log.debug(sql, "updateLogAttemptByEmail");
        this.execute(sql);
    }

    private Integer getTotalAttemptsByEmail(String email) throws Exception {
        var sql = String.format("""
        -- getTotalAttemptsByEmail
        SELECT log_attempts
        FROM base_user
        WHERE 1=1
        AND delete_date IS NULL
        AND email = '%s'
        """, email);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return 0;
        return Integer.parseInt(list.getFirst().get("log_attempts"));
    }
}