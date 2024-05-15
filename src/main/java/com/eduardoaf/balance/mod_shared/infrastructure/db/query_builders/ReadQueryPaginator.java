package com.eduardoaf.balance.mod_shared.infrastructure.db.query_builders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.eduardoaf.balance.mod_shared.infrastructure.db.MySql;

public class ReadQueryPaginator {

    private MySql mysql = null;
    private ReadQuery readQuery = null;

    private int pageNumber = 1;
    private int pageSize = 50;

    private int totalRows = 0;
    private int offsetStart = 0;
    private int offsetPageSize = 50; //[1 - n]
    private int totalPages = 0;

    private int fullPages = 0;
    private int itemsInLastPage = 0;

    private String sql = "";
    private String sqlCount = "";

    private List<Map<String, String>> rows = new ArrayList<>();

    public ReadQueryPaginator(ReadQuery readQuery, int pageNumber, int pageSize) {
        this.readQuery = readQuery;

        this.pageNumber = pageNumber;
        if (this.pageNumber < 1) this.pageNumber = 1;
        if (pageSize > 0) this.pageSize = pageSize;

        //this.mysql = MySql.GetEmailInstanceByEnv();
    }

    public ReadQueryPaginator(ReadQuery readQuery, int pageNumber, int pageSize, MySql mysql) {
        this.readQuery = readQuery;

        this.pageNumber = pageNumber;
        if (this.pageNumber < 1) this.pageNumber = 1;
        if (pageSize > 0) this.pageSize = pageSize;

        this.mysql = mysql;
    }

    public static ReadQueryPaginator fromPrimitives(ReadQuery readQuery, int pageNumber, int pageSize) {
        return new ReadQueryPaginator(readQuery, pageNumber, pageSize);
    }

    public static ReadQueryPaginator fromPrimitives(ReadQuery readQuery, int pageNumber, int pageSize, MySql mysql) {
        return new ReadQueryPaginator(readQuery, pageNumber, pageSize, mysql);
    }

    public ReadQueryPaginator calculate() throws Exception {
        readQuery.select();

        sqlCount = readQuery.getSqlCount();
        //Lg.sql(sqlCount, "_sqlCount");

        loadTotalRows();
        if (totalRows == 0) return this;

        loadPages();
        loadOffsetStart();

        readQuery.setOffset(offsetStart, offsetPageSize);
        sql = readQuery.select().getSql();
        //Lg.sql(sql, "_sql");

        // Execute the query
        rows = mysql.query(sql);

        return this;
    }

    private void loadTotalRows() throws Exception {
        List<Map<String, String>> resultTotal = mysql.query(sqlCount);
        if (resultTotal.isEmpty()) return;

        Map<String, String> dict = resultTotal.get(0);
        totalRows = Integer.parseInt(dict.get("total"));
    }

    private void loadPages() {
        if (pageSize >= totalRows) {
            fullPages = 1;
            totalPages = 1;
            offsetStart = 0;
            pageNumber = 1;
            offsetPageSize = totalRows;
            return;
        }

        offsetPageSize = pageSize;
        fullPages = totalRows / pageSize;
        itemsInLastPage = totalRows % pageSize;

        totalPages = fullPages;
        if (itemsInLastPage > 0)
            totalPages = fullPages + 1;

        if (pageNumber > totalPages)
            pageNumber = totalPages;
    }

    private void loadOffsetStart() {
        offsetStart = (pageNumber - 1) * offsetPageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getNextPage() {
        if (pageNumber < totalPages)
            return pageNumber + 1;
        return totalPages;
    }

    public List<Map<String, String>> getRows() {
        return rows;
    }

    public int getTotalCount() {
        return totalRows;
    }

    public int getCurrentPage() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalItemsInPage() {
        return rows.size();
    }
}