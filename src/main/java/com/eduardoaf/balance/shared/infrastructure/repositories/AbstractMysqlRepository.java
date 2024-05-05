package com.eduardoaf.balance.shared.infrastructure.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.eduardoaf.balance.shared.infrastructure.db.contexts.MysqlContextDto;
import com.eduardoaf.balance.shared.infrastructure.db.MySql;

public abstract class AbstractMysqlRepository {

    @Autowired
    private Environment env;
    private MySql mySql = null;

    protected int lastInsertId = -1;
    protected int rowsAffected = -1;

    private void loadConnection() {
        if (mySql != null) return;

        String connectionString = env.getProperty("spring.datasource.url");
        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");
        var mysqlDto = MysqlContextDto.getInstance(connectionString, username, password);
        mySql = new MySql(mysqlDto);
    }

    protected boolean execute(String query) throws Exception {
        loadConnection();
        var result = mySql.execute(query);
        lastInsertId = mySql.getLastInsertId();
        rowsAffected = mySql.getRowsAffected();
        return result;
    }

    protected List<Map<String, String>> query(String query) throws Exception {
        loadConnection();
        return mySql.query(query);
    }
}
