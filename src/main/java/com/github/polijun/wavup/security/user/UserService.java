package com.github.polijun.wavup.security.user;


import java.util.List;
import org.springframework.lang.NonNull;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(@NonNull Long userId);

    User getUserByEmail(String userEmail);

    User createUser(@NonNull User user);

    void updateUser(@NonNull Long userId, @NonNull User updatedUser);

    void deleteUser(@NonNull Long userId);

}
