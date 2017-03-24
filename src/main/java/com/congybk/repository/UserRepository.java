package com.congybk.repository;

import com.congybk.entity.User;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

/**
 * @author YNC
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findByCardId(String cardId);
}
