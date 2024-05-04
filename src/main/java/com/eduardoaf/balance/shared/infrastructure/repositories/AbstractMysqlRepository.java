package com.eduardoaf.balance.shared.infrastructure.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public abstract class AbstractMysqlRepository<T> {

    private final JdbcTemplate jdbcTemplate;

    protected AbstractMysqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    protected void write(String sql) {
        jdbcTemplate.update(sql);
    }

    public List<T> getData(String sql, RowMapper<T> rowMapper) {
        return jdbcTemplate.query(sql, rowMapper);
    }
}
