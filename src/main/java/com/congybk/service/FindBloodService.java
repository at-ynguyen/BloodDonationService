package com.congybk.service;

import com.congybk.entity.FindBlood;

import java.util.List;

/**
 * @Author YNC on 19/04/2017.
 */
public interface FindBloodService {
    List<FindBlood> getAll();

    void delete(int id);

    FindBlood findById(int id);

    FindBlood update(FindBlood findBlood);

    FindBlood create(FindBlood findBlood);

    List<FindBlood> getFindBlood(int start, int end);

    long getCount();
}
