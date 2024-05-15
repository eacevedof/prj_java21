package com.eduardoaf.balance.mod_users.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.UuidFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.DateFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.repositories.AbstractMysqlRepository;
import com.eduardoaf.balance.mod_shared.infrastructure.db.query_builders.InsertQuery;

import com.eduardoaf.balance.mod_users.domain.entities.BaseUserEntity;

@Component
public final class BaseUserWriterRepository extends AbstractMysqlRepository {

    private final Log log;
    private final DateFormatter dateFormatter;
    private final UuidFormatter uuid;

    @Autowired
    public BaseUserWriterRepository(
            Log log,
            DateFormatter dateFormatter,
            UuidFormatter uuid
    ) {
        this.log = log;
        this.dateFormatter = dateFormatter;
        this.uuid = uuid;
    }

    public void createNewUser(BaseUserEntity baseUserEntity) throws Exception {
        var sql = InsertQuery.getInstance("base_user")
                .addColumn("insert_platform", baseUserEntity.getInsertPlatform())
                .addColumn("insert_user", baseUserEntity.getInsertUser())
                .addColumn("insert_date", dateFormatter.getNow())
                .addColumn("uuid", uuid.getUuid("USR", 15))
                .addColumn("code_erp", baseUserEntity.getCodeErp())
                //.addColumn("description", sysUserEntity.description)
                .addColumn("email", baseUserEntity.getEmail())
                .addColumn("secret", baseUserEntity.getSecret())
                .addColumn("phone", baseUserEntity.getPhone())
                .addColumn("fullname", baseUserEntity.getFullname())
                .addColumn("address", baseUserEntity.getAddress())
                .addColumn("birthdate", baseUserEntity.getBirthdate())
                .addColumn("id_parent", baseUserEntity.getInsertUser())
                .addColumn("id_gender", baseUserEntity.getIdGender())
                .addColumn("id_nationality", baseUserEntity.getIdNationality())
                .addColumn("id_country", baseUserEntity.getIdCountry())
                .addColumn("id_language", baseUserEntity.getIdLanguage())
                .addColumn("id_profile", baseUserEntity.getIdProfile())
                .addColumn("url_picture", null)
                .addColumn("date_validated", null)
                .addColumn("log_attempts", 0)
                .getQuery();
        log.debug(sql, "createNewUser");
        this.execute(sql);
    }
}