package com.eduardoaf.balance.mod_shared.domain.entities;

import java.util.Map;

import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;

public abstract class AbstractEntity {

    public String processFlag;
    public String insertPlatform;
    public String insertUser;
    public String insertDate;
    public String updatePlatform;
    public String updateUser;
    public String updateDate;
    public String deletePlatform;
    public String deleteUser;
    public String deleteDate;
    public String cruCsvNote;
    public String isErpSent;
    public String isEnabled;
    public Integer i;
    public Integer id;

    protected static void loadParentByMapRow(
        AbstractEntity entity,
        Map<String, String> mapRow
    ) {
        StringFormatter stringFormatter = StringFormatter.getInstance();
        NumberFormatter numberFormatter = NumberFormatter.getInstance();

        entity.processFlag =  stringFormatter.getTrimOrNull(mapRow.get("process_flag"));
        entity.insertPlatform =  stringFormatter.getTrimOrNull(mapRow.get("insert_platform"));
        entity.insertUser =  stringFormatter.getTrimOrNull(mapRow.get("insert_user"));
        entity.insertDate =  stringFormatter.getTrimOrNull(mapRow.get("insert_date"));
        entity.updatePlatform =  stringFormatter.getTrimOrNull(mapRow.get("update_platform"));
        entity.updateUser =  stringFormatter.getTrimOrNull(mapRow.get("update_user"));
        entity.updateDate =  stringFormatter.getTrimOrNull(mapRow.get("update_date"));
        entity.deletePlatform =  stringFormatter.getTrimOrNull(mapRow.get("delete_platform"));
        entity.deleteUser =  stringFormatter.getTrimOrNull(mapRow.get("delete_user"));
        entity.deleteDate =  stringFormatter.getTrimOrNull(mapRow.get("delete_date"));
        entity.cruCsvNote =  stringFormatter.getTrimOrNull(mapRow.get("cru_csv_note"));
        entity.isErpSent =  stringFormatter.getTrimOrNull(mapRow.get("is_erp_sent"));
        entity.isEnabled =  stringFormatter.getTrimOrNull(mapRow.get("is_enabled"));
        entity.i =  numberFormatter.getIntegerOrNull(mapRow.get("i"));
        entity.id =  numberFormatter.getIntegerOrNull(mapRow.get("id"));
    }

}
