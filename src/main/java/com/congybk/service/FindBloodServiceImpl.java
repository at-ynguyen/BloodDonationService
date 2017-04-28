package com.congybk.service;

import com.congybk.entity.FindBlood;
import com.congybk.repository.FindBloodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author YNC on 19/04/2017.
 */
@Service
public class FindBloodServiceImpl implements FindBloodService {
    @Autowired
    FindBloodRepository findBloodRepository;

    @Override
    public List<FindBlood> getAll() {
        return (List<FindBlood>) findBloodRepository.findAll();
    }

    @Override
    public void delete(int id) {
        findBloodRepository.delete(id);
    }

    @Override
    public FindBlood findById(int id) {
        return findBloodRepository.findOne(id);
    }

    @Override
    public FindBlood update(FindBlood findBlood) {
        return findBloodRepository.save(findBlood);
    }

    @Override
    public FindBlood create(FindBlood findBlood) {
        return findBloodRepository.save(findBlood);
    }

    @Override
    public List<FindBlood> getFindBlood(int start, int end) {
        return findBloodRepository.getListFindBlood(start, end);
    }

    @Override
    public long getCount() {
        return findBloodRepository.count();
    }
}
