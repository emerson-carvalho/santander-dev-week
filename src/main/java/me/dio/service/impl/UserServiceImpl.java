package me.dio.service.impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        if (userRepository.findAll().isEmpty()) {
            throw new NoSuchElementException("Lista de usuários vazia!");
        }
        return userList;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado!"));
    }

    @Override
    public List<User> findByNameStartingWith(String prefixName) {
        List<User> userList = userRepository.findByNameStartingWith(prefixName);
        if (userList.isEmpty()) {
            throw new NoSuchElementException("Nenhum usuário encontrado com o prefixo indicado!");
        }
        return userList;
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("Número de conta existente!");
        }
        return userRepository.save(userToCreate);
    }

    @Override
    public User update(Long id, User userToUpdate) {
        User user = findById(id);
        user.updateUser(userToUpdate);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }
}
