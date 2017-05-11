package com.congybk.webapp.model;

/**
 * @Author YNC on 01/05/2017.
 */
public class SearchHistory {
    private String query;

    public SearchHistory() {
    }

    public SearchHistory(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
