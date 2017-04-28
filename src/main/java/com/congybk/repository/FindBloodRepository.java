package com.congybk.repository;

import com.congybk.entity.Event;
import com.congybk.entity.FindBlood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author YNC on 19/04/2017.
 */
@Repository
public interface FindBloodRepository extends CrudRepository<FindBlood, Integer> {
    @Query(value = "SELECT * FROM find_blood ORDER BY create_at DESC LIMIT :start,:ends  ", nativeQuery = true)
    List<FindBlood> getListFindBlood(@Param("start") int start, @Param("ends") int end);
}
