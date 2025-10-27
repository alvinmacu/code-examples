package mx.com.alvin.ms.ms_users.service;

import mx.com.alvin.ms.ms_users.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    Optional<User> getUserById(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
    Optional<User> getUserByEmail(String email);
}
