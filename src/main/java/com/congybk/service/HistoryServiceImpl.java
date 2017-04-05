package com.congybk.service;

import com.congybk.entity.History;
import com.congybk.entity.User;
import com.congybk.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author YNC on 03/04/2017.
 */
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryRepository mHistoryRepository;

    @Override
    public List<History> getAll() {
        return (List<History>) mHistoryRepository.findAll();
    }

    @Override
    public void delete(int id) {
        mHistoryRepository.delete(id);
    }

    @Override
    public History findById(int id) {
        return mHistoryRepository.findOne(id);
    }

    @Override
    public History update(History History) {
        return mHistoryRepository.save(History);
    }

    @Override
    public History create(History History) {
        return mHistoryRepository.save(History);
    }

    @Override
    public List<History> getListHistoryByUser(User user) {
        return mHistoryRepository.getListHistoryByUser(user);
    }
}
