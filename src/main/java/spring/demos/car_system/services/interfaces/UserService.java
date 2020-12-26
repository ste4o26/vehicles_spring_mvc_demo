package spring.demos.car_system.services.interfaces;

import spring.demos.car_system.domain.entities.User;

import java.util.List;

public interface UserService {
    //TODO ADD METHODS AND CREATE AN IMPLEMENTATION
    List<User> getAllUsers();

    User getUserByUsername(String username);

    User getUserById(Long id);

    User createUser(User user);

    User updateUser(User user);

    User deleteUser(Long id);

    Long getUsersCount();
}
