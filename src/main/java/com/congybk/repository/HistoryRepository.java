package com.congybk.repository;

import com.congybk.entity.History;
import com.congybk.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author YNC on 03/04/2017.
 */
@Repository
public interface HistoryRepository extends CrudRepository<History, Integer> {
    List<History> getListHistoryByUser(User user);

    @Query(value = "SELECT user_id FROM history GROUP BY user_id ORDER BY COUNT(id) DESC LIMIT 10", nativeQuery = true)
    List<Integer> getTopUser();

    @Query(value = "SELECT COUNT(id) FROM history WHERE user_id = :userId", nativeQuery = true)
    int getNumberDonationByUser(@Param("userId") int userId);

    @Query(value = "SELECT user_id FROM history GROUP BY user_id ORDER BY COUNT(id) DESC LIMIT :start,10", nativeQuery = true)
    List<Integer> getUserHistory(@Param("start") int start);
}
