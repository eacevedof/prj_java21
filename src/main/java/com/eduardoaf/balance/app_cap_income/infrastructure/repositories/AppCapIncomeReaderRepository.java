package com.eduardoaf.balance.app_cap_income.infrastructure.repositories;

import com.eduardoaf.balance.shared.infrastructure.repositories.AbstractMysqlRepository;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eduardoaf.balance.shared.infrastructure.file.Log;

import java.util.Map;

@Component
public final class AppCapIncomeReaderRepository extends AbstractMysqlRepository {

    private final Log log;

    @Autowired
    public AppCapIncomeReaderRepository (Log log) {
        this.log = log;
    }

    public Map<String, String> getIncomeByIncomeId(int incomeId) throws Exception {
        String sql = String.format("""
        -- getIncomeByIncomeId
        SELECT * 
        FROM app_cap_income
        WHERE 1=1
        AND delete_date IS NULL
        AND id = %s
        """, incomeId);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return Collections.emptyMap();
        return list.getFirst();
    }
}
