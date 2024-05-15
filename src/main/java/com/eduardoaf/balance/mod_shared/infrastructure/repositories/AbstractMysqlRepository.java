package com.eduardoaf.balance.mod_shared.infrastructure.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Getter;

import com.eduardoaf.balance.mod_shared.infrastructure.db.contexts.MysqlContextDto;
import com.eduardoaf.balance.mod_shared.infrastructure.db.MySql;

public abstract class AbstractMysqlRepository {

    @Autowired
    private Environment env;
    private MySql mySql = null;

    @Getter
    protected int lastInsertId = -1;
    @Getter
    protected int rowsAffected = -1;

    private void loadConnection() {
        if (mySql != null) return;

        String connectionString = env.getProperty("spring.datasource.url");
        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");

        var mysqlDto = MysqlContextDto.getInstance(connectionString, username, password);
        mySql = new MySql(mysqlDto);
    }

    protected void execute(String query) throws Exception {
        loadConnection();
        mySql.execute(query);
        lastInsertId = mySql.getLastInsertId();
        rowsAffected = mySql.getRowsAffected();
    }

    protected List<Map<String, String>> query(String query) throws Exception {
        loadConnection();
        return mySql.query(query);
    }

    protected int getAsInt(String value) {
        if (value == null || value.isEmpty())
            return 0;
        return Integer.parseInt(value);
    }
}
