package com.congybk.repository;

import com.congybk.entity.History;
import com.congybk.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author YNC on 03/04/2017.
 */
@Repository
public interface HistoryRepository extends CrudRepository<History, Integer> {
    List<History> getListHistoryByUser(User user);
}
