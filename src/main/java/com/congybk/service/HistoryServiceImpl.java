package com.congybk.service;

import com.congybk.entity.History;
import com.congybk.entity.User;
import com.congybk.repository.HistoryRepository;
import com.congybk.response.TopHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author YNC on 03/04/2017.
 */
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryRepository mHistoryRepository;
    @Autowired
    UserService mUserService;

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

    @Override
    public List<TopHistory> getTopHistory() {
        List<TopHistory> topHistory = new ArrayList<>();
        List<Integer> userIds = mHistoryRepository.getTopUser();
        for (int i = 0; i < userIds.size(); i++) {
            topHistory.add(new TopHistory(mHistoryRepository.getNumberDonationByUser(userIds.get(i)), mUserService.findById(userIds.get(i))));
        }
        return topHistory;
    }

    @Override
    public long getCount() {
        return mHistoryRepository.count();
    }

    @Override
    public List<TopHistory> getHistorys(int start) {
        List<TopHistory> topHistory = new ArrayList<>();
        List<Integer> userIds = mHistoryRepository.getUserHistory(start);
        for (int i = 0; i < userIds.size(); i++) {
            topHistory.add(new TopHistory(mHistoryRepository.getNumberDonationByUser(userIds.get(i)), mUserService.findById(userIds.get(i))));
        }
        return topHistory;
    }

    @Override
    public int getNumberDonationByUser(User user) {
        return mHistoryRepository.getNumberDonationByUser(user.getId());
    }
}
