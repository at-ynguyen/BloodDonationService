package com.congybk.service;

import com.congybk.entity.Role;
import com.congybk.entity.User;


import java.util.List;

/**
 * @author YNC
 */
public interface UserService {
    User create(User user);

    User findById(int id);

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    User update(User user);

    void delete(int id);

    List<User> getAll();

    User save(User user);

    User findByCardId(String cardId);

    User updatePassword(User user);

    List<Role> getAllRoleByUserId(int id);

    List<User> getListUser(int start);

    List<User> findByBloodType(String bloodType);

    List<User> findByTownId(int townId);

    List<User> findByTownIdAndBloodType(int townId, String bloodType);

    long getCount();

    List<User> findUserByFullnameOrCardId(String query);

}
