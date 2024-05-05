package com.eduardoaf.balance.shared.infrastructure.db.query_builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadQuery {

    private String comment = "";
    private String table = "";

    private Integer top = null;
    private boolean isDistinct = false;
    private final List<String> getFields = new ArrayList<>();
    private final List<String> joins = new ArrayList<>();
    private final List<String> where = new ArrayList<>();
    private final List<String> groupBy = new ArrayList<>();
    private final List<String> having = new ArrayList<>();
    private final Map<String, String> orderBy = new HashMap<>();
    private final Map<String, Integer> offset = new HashMap<>();

    private final List<String> numeric = new ArrayList<>();

    private String sql = "";
    private String sqlCount = "";

    private final List<String> reserved = List.of("get", "order", "password");

    private static final String READ = "r";
    public static final String ORDER_ASC = "ASC";
    public static final String ORDER_DESC = "DESC";

    private String rawSelect = "";

    public ReadQuery(String table) {
        this.table = table;
    }

    public static ReadQuery fromTable(String table) {
        return new ReadQuery(table);
    }

    public static ReadQuery fromRawSelect(String rawSelect) {
        ReadQuery query = new ReadQuery("");
        query.setRawSelect(rawSelect);
        return query;
    }

    private String getFields() {
        if (getFields.isEmpty())
            return "*";
        return String.join(", ", getFields);
    }

    private String getJoins() {
        if (joins.isEmpty())
            return "";
        return String.join("\n", joins);
    }

    private String getWhere() {
        String whereClause = "\nWHERE 1=1 ";
        if (where.isEmpty())
            return whereClause;
        return whereClause + "AND " + String.join("\nAND ", where);
    }

    private String getGroupBy() {
        if (groupBy.isEmpty())
            return "";
        return "\nGROUP BY " + String.join(", ", groupBy);
    }

    private String getHaving() {
        if (having.isEmpty())
            return "";
        return "\nHAVING " + String.join(", ", having);
    }

    private String getOrderBy() {
        if (orderBy.isEmpty())
            return "";
        List<String> orderByList = new ArrayList<>();
        orderBy.forEach((key, value) -> orderByList.add(key + " " + value));
        return "\nORDER BY " + String.join(", ", orderByList);
    }

    private String getLimit() {
        if (offset.isEmpty())
            return "";

        if (orderBy.isEmpty())
            throw new RuntimeException("For pagination, an order by field is required");

        offset.putIfAbsent("regfrom", 0);
        offset.putIfAbsent("pagesize", 50);

        int start = offset.get("regfrom");
        int pageSize = offset.get("pagesize");

        return String.format("\nOFFSET %d ROWS\nFETCH NEXT %d ROWS ONLY", start, pageSize);
    }

    private boolean isNumeric(String fieldName) {
        return numeric.contains(fieldName);
    }

    private boolean isReserved(String word) {
        return reserved.contains(word.toLowerCase());
    }

    private void cleanReserved(Object mxFields) {
        if (mxFields instanceof String) {
            String field = (String) mxFields;
            if (isReserved(field))
                mxFields = "[" + field + "]";
            return;
        }

        if (mxFields instanceof List<?>) {
            List<String> temp = (List<String>) mxFields;
            for (int i = 0; i < temp.size(); i++) {
                String field = temp.get(i);
                if (isReserved(field))
                    temp.set(i, "[" + field + "]");
            }
            mxFields = temp;
        }
    }

    public ReadQuery setRawSelect(String rawSelect) {
        this.rawSelect = rawSelect;
        return this;
    }

    public ReadQuery setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ReadQuery distinct() {
        isDistinct = true;
        return this;
    }

    public ReadQuery top(int top) {
        this.top = top;
        return this;
    }

    public ReadQuery addGetField(String fieldName) {
        getFields.add(fieldName);
        return this;
    }

    public ReadQuery addJoin(String join) {
        joins.add(join);
        return this;
    }

    public ReadQuery addWhere(String whereClause) {
        where.add(whereClause);
        return this;
    }

    public ReadQuery addGroupBy(String groupByClause) {
        groupBy.add(groupByClause);
        return this;
    }

    public ReadQuery addHaving(String havingClause) {
        having.add(havingClause);
        return this;
    }

    public ReadQuery addOrderBy(String field, String order) {
        orderBy.put(field, order);
        return this;
    }

    public ReadQuery setOffset(int start, int pageSize) {
        offset.put("regfrom", start);
        offset.put("pagesize", pageSize);
        return this;
    }

    public ReadQuery select() throws Exception {
        List<String> select = new ArrayList<>();
        sql = "/*error select*/";
        sqlCount = "/*error select count*/";
        if (table.isEmpty() && rawSelect.isEmpty())
            throw new Exception("Missing table in select");

        String comment = this.comment.isEmpty() ? "/*select*/" : "/*" + this.comment + "*/";
        select.add(comment + " SELECT");
        if (isDistinct) select.add("DISTINCT");
        if (top != null) select.add("TOP " + top);

        select.add(getFields());
        select.add("\nFROM " + table + "\n");
        select.add(getJoins());
        select.add(getWhere());
        select.add(getGroupBy());
        select.add(getHaving());

        sqlCount = String.join(" ", select);
        if (!rawSelect.isEmpty())
            sqlCount = rawSelect;

        sqlCount = """
        SELECT COUNT(*) total
        FROM (
            %s
        ) t
        """.formatted(sqlCount);

        select.add(getOrderBy());
        select.add(getLimit());
        sql = String.join(" ", select);
        if (!rawSelect.isEmpty())
            sql = rawSelect;
        return this;
    }

    public String getSql() {
        return sql;
    }

    public String getSqlCount() {
        return sqlCount;
    }
}
