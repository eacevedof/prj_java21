package com.eduardoaf.balance.shared.infrastructure.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractMysqlRepository {

    private final JdbcTemplate jdbcTemplate;

    public AbstractMysqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    protected void write(String sql) {
        jdbcTemplate.update(sql);
    }

    protected List<T> getData(RowMapper<T> rowMapper, String sql) {
        return jdbcTemplate.query(sql, rowMapper);
    }
}
