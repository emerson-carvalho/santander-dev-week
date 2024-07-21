package me.dio.service;

import me.dio.domain.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById (Long id);

    List<User> findByNameStartingWith(String name);

    User create(User userToCreate);

    User update(Long id, User userToUpdate);

    void delete(Long id);
}
