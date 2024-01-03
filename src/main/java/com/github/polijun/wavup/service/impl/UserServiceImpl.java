package com.github.polijun.wavup.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.github.polijun.wavup.exception.ResourceNotFound;
import com.github.polijun.wavup.exception.UserAlreadyExists;
import com.github.polijun.wavup.model.User;
import com.github.polijun.wavup.repository.UserRepository;
import com.github.polijun.wavup.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User"));
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new UserAlreadyExists("User");
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User savedUser =
                userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User"));
        savedUser.setUsername(user.getUsername());
        savedUser.setEmail(user.getEmail());
        savedUser.setPassword(user.getPassword());
        savedUser.setRole(user.getRole());

        return userRepository.save(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User delUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User"));
        userRepository.delete(delUser);
    }

    @Override
    public User validateUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new ResourceNotFound("User"));
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFound("User"));
    }

}
