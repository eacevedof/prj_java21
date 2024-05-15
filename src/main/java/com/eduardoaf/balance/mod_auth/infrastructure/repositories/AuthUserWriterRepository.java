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
                .addColumn("update_platform", authUserEntity.insertPlatform)
                .addColumn("update_user", authUserEntity.insertUser)
                .addColumn("update_date", dateFormatter.getNow())
                .addColumn("log_attempts", 0)
                .where("id", stringFormatter.getAlwaysString(authUserEntity.id))
                .getQuery();
        log.debug(sql, "updateUserLogged");
        this.execute(sql);
    }
}