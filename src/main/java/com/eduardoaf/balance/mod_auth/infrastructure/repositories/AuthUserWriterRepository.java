package com.eduardoaf.balance.mod_auth.infrastructure.repositories;

import com.eduardoaf.balance.mod_auth.domain.entities.SysUserEntity;
import com.eduardoaf.balance.mod_shared.infrastructure.db.query_builders.InsertQuery;
import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.DateFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.UuidFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.repositories.AbstractMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class AuthUserWriterRepository extends AbstractMysqlRepository {

    private final Log log;
    private final DateFormatter dateFormatter;
    private final UuidFormatter uuid;

    @Autowired
    public AuthUserWriterRepository(
            Log log,
            DateFormatter dateFormatter,
            UuidFormatter uuid
    ) {
        this.log = log;
        this.dateFormatter = dateFormatter;
        this.uuid = uuid;
    }

    public void createNewUser(SysUserEntity sysUserEntity) throws Exception {
        var sql = InsertQuery.getInstance("base_user")
                .addColumn("insert_platform", sysUserEntity.insertPlatform)
                .addColumn("insert_user", sysUserEntity.insertUser)
                .addColumn("insert_date", dateFormatter.getNow())
                .addColumn("uuid", uuid.getUuid("USR", 15))
                .addColumn("code_erp", sysUserEntity.codeErp)
                //.addColumn("description", sysUserEntity.description)
                .addColumn("email", sysUserEntity.email)
                .addColumn("secret", sysUserEntity.secret)
                .addColumn("phone", sysUserEntity.phone)
                .addColumn("fullname", sysUserEntity.fullname)
                .addColumn("address", sysUserEntity.address)
                .addColumn("birthdate", sysUserEntity.birthdate)
                .addColumn("id_parent", sysUserEntity.idParent)
                .addColumn("id_gender", sysUserEntity.idGender)
                .addColumn("id_nationality", sysUserEntity.idNationality)
                .addColumn("id_country", sysUserEntity.idCountry)
                .addColumn("id_language", sysUserEntity.idLanguage)
                .addColumn("id_profile", sysUserEntity.idProfile)
                .addColumn("url_picture", null)
                .addColumn("date_validated", null)
                .addColumn("log_attempts", 0)
                .getQuery();
        log.debug(sql, "createNewUser");
        this.execute(sql);
    }
}