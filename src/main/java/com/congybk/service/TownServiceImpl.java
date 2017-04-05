package com.congybk.service;

import com.congybk.entity.Town;
import com.congybk.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author YNC on 02/04/2017.
 */
@Service
public class TownServiceImpl implements TownService {
    @Autowired
    TownRepository mTownRepository;

    @Override
    public List<Town> getAll() {
        return (List<Town>) mTownRepository.findAll();
    }

    @Override
    public void delete(int id) {
        mTownRepository.delete(id);
    }

    @Override
    public Town findById(int id) {
        return mTownRepository.findOne(id);
    }

    @Override
    public Town update(Town town) {
        return mTownRepository.save(town);
    }

    @Override
    public Town create(Town town) {
        return mTownRepository.save(town);
    }
}
