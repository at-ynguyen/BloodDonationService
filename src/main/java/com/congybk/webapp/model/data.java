package com.congybk.webapp.model;

import com.congybk.entity.History;

import java.util.List;

/**
 * @Author YNC on 04/04/2017.
 */
public class data {
    private List<History> data;

    public data() {
    }

    public data(List<History> listHistoryByUser) {
        data = listHistoryByUser;
    }

    public List<History> getData() {
        return data;
    }

    public void setData(List<History> data) {
        this.data = data;
    }
}
