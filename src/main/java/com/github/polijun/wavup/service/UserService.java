package com.github.polijun.wavup.service;

import java.util.List;
import com.github.polijun.wavup.model.User;

public interface UserService {
    List<User> findAllUsers();

    User findUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    User findUserByUsername(String username);

    User validateUserByUsernameAndPassword(String username, String password);
}
