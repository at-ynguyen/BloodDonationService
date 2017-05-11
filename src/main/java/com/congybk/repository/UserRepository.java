package com.congybk.repository;

import com.congybk.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YNC
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findByCardId(String Id);

    List<User> findByBloodType(String bloodType);

    @Query(value = "SELECT * FROM user LIMIT :start,10  ", nativeQuery = true)
    List<User> getListUser(@Param("start") int start);

    @Query(value = "SELECT * FROM user WHERE town_id=:id ", nativeQuery = true)
    List<User> findByTownId(@Param("id") int id);

    @Query(value = "SELECT * FROM user WHERE town_id=:id AND blood_type=:bloodType", nativeQuery = true)
    List<User> findByTownIdAndBloodType(@Param("id") int id, @Param("bloodType") String bloodType);

    @Query("SELECT u FROM User u WHERE (u.fullName LIKE LOWER(CONCAT('%', ?1, '%')) OR (u.cardId LIKE LOWER(CONCAT('%', ?1, '%') ) ))")
    List<User> findUserByFullnameOrCardId(String query);
}
