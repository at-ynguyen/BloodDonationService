package com.congybk.service;

import com.congybk.entity.Role;
import com.congybk.entity.User;
import com.congybk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YNC
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository mUserRepository;

    @Override
    public User create(User user) {
        return mUserRepository.save(user);
    }

    @Override
    public User findById(int id) {
        return mUserRepository.findOne(id);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return mUserRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User findByEmail(String email) {
        return mUserRepository.findByEmail(email);
    }

    @Override
    public User update(User user) {
        return mUserRepository.save(user);
    }

    @Override
    public void delete(int id) {
        mUserRepository.delete(id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) mUserRepository.findAll();
    }

    @Override
    public User save(User user) {
        return mUserRepository.save(user);
    }

    @Override
    public User findByCardId(String cardId) {
        return mUserRepository.findByCardId(cardId);
    }

    @Override
    public User updatePassword(User user) {
        return null;
    }

    @Override
    public List<Role> getAllRoleByUserId(int id) {
        return this.mUserRepository.findOne(id).getPermissionList().stream().map(x -> x.getRole()).collect(Collectors.toList());
    }

    @Override
    public List<User> getListUser(int start) {
        return mUserRepository.getListUser(start);
    }

    @Override
    public List<User> findByBloodType(String bloodType) {
        return mUserRepository.findByBloodType(bloodType);
    }

    @Override
    public List<User> findByTownId(int townId) {
        return mUserRepository.findByTownId(townId);
    }

    @Override
    public List<User> findByTownIdAndBloodType(int townId, String bloodType) {
        return mUserRepository.findByTownIdAndBloodType(townId, bloodType);
    }

    @Override
    public long getCount() {
        return mUserRepository.count();
    }

    @Override
    public List<User> findUserByFullnameOrCardId(String query) {
        return mUserRepository.findUserByFullnameOrCardId(query);
    }


}
