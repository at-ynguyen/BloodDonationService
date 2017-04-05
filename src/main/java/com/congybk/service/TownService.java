package com.congybk.service;

import com.congybk.entity.Town;

import java.util.List;

/**
 * @Author YNC on 02/04/2017.
 */
public interface TownService {
    List<Town> getAll();

    void delete(int id);

    Town findById(int id);

    Town update(Town organization);

    Town create(Town organization);
}
