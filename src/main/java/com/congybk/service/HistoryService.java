package com.congybk.service;

import com.congybk.entity.History;
import com.congybk.entity.User;

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
}
