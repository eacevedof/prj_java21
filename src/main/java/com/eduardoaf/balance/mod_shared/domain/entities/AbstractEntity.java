package com.eduardoaf.balance.mod_shared.domain.entities;

import java.util.Map;

import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;

import lombok.Getter;


@Getter
public abstract class AbstractEntity {

    protected String processFlag;
    protected String insertPlatform;
    protected String insertUser;
    protected String insertDate;
    protected String updatePlatform;
    protected String updateUser;
    protected String updateDate;
    protected String deletePlatform;
    protected String deleteUser;
    protected String deleteDate;
    protected String cruCsvNote;
    protected String isErpSent;
    protected String isEnabled;
    protected Integer i;
    protected Integer id;
    protected String description;

    protected static void loadParentByMapRow(
        AbstractEntity abstractEntity,
        Map<String, String> mapRow
    ) {
        StringFormatter stringFormatter = StringFormatter.getInstance();
        NumberFormatter numberFormatter = NumberFormatter.getInstance();

        abstractEntity.processFlag =  stringFormatter.getTrimOrNull(mapRow.get("process_flag"));
        abstractEntity.insertPlatform =  stringFormatter.getTrimOrNull(mapRow.get("insert_platform"));
        abstractEntity.insertUser =  stringFormatter.getTrimOrNull(mapRow.get("insert_user"));
        abstractEntity.insertDate =  stringFormatter.getTrimOrNull(mapRow.get("insert_date"));
        abstractEntity.updatePlatform =  stringFormatter.getTrimOrNull(mapRow.get("update_platform"));
        abstractEntity.updateUser =  stringFormatter.getTrimOrNull(mapRow.get("update_user"));
        abstractEntity.updateDate =  stringFormatter.getTrimOrNull(mapRow.get("update_date"));
        abstractEntity.deletePlatform =  stringFormatter.getTrimOrNull(mapRow.get("delete_platform"));
        abstractEntity.deleteUser =  stringFormatter.getTrimOrNull(mapRow.get("delete_user"));
        abstractEntity.deleteDate =  stringFormatter.getTrimOrNull(mapRow.get("delete_date"));
        abstractEntity.cruCsvNote =  stringFormatter.getTrimOrNull(mapRow.get("cru_csv_note"));
        abstractEntity.isErpSent =  stringFormatter.getTrimOrNull(mapRow.get("is_erp_sent"));
        abstractEntity.isEnabled =  stringFormatter.getTrimOrNull(mapRow.get("is_enabled"));
        abstractEntity.i =  numberFormatter.getIntegerOrNull(mapRow.get("i"));
        abstractEntity.id =  numberFormatter.getIntegerOrNull(mapRow.get("id"));
        abstractEntity.description =  stringFormatter.getTrimOrNull(mapRow.get("description"));
    }

}
