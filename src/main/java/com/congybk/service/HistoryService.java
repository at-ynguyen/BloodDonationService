package com.congybk.service;

import com.congybk.entity.History;
import com.congybk.entity.User;
import com.congybk.response.TopHistory;

import java.util.List;

/**
 * @Author YNC on 03/04/2017.
 */
public interface HistoryService {
    List<History> getAll();

    void delete(int id);

    History findById(int id);

    History update(History History);

    History create(History History);

    List<History> getListHistoryByUser(User user);

    List<TopHistory> getTopHistory();

    long getCount();

    List<TopHistory> getHistorys(int start);

    int getNumberDonationByUser(User user);
}
