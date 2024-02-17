package com.github.polijun.wavup.security.user;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.polijun.wavup.exception.AlreadyExistsException;
import com.github.polijun.wavup.exception.NonExistsException;
import com.github.polijun.wavup.model.Order;
import lombok.RequiredArgsConstructor;

/**
 * UserServiceImpl
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private static final String USER = "User";

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(@NonNull Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NonExistsException(USER));
    }

    @Override
    public User getUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NonExistsException(USER));
    }

    @Override
    public User createUser(@NonNull User user) {
        String email = Objects.requireNonNull(user.getEmail());
        Optional<User> userExists = userRepository.findByEmail(email);
        if (!userExists.isPresent()) {
            return userRepository.save(user);
        } else {
            throw new AlreadyExistsException(USER);
        }
    }

    @Override
    @Transactional
    public void updateUser(@NonNull Long userId, @NonNull User updatedUser) {
        User userExists =
                userRepository.findById(userId).orElseThrow(() -> new NonExistsException(USER));
        userExists.setEmail(updatedUser.getEmail());
        userExists.setFirstName(updatedUser.getFirstName());
        userExists.setLastName(updatedUser.getLastName());
        userExists.setOrders(updatedUser.getOrders());
        userExists.setPassword(updatedUser.getPassword());
        userExists.setRole(updatedUser.getRole());
    }

    @Override
    public void deleteUser(@NonNull Long userId) {
        Optional<User> userExists = userRepository.findById(userId);
        if (userExists.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new NonExistsException(USER);
        }
    }


}
