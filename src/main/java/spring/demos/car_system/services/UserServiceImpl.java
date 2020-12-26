package spring.demos.car_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.demos.car_system.domain.entities.User;
import spring.demos.car_system.domain.entities.enums.Role;
import spring.demos.car_system.repositories.UserRepository;
import spring.demos.car_system.services.interfaces.UserService;
import spring.demos.car_system.init.ErrorMessage;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository
                .findAll(Sort.by("username"));
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.
                        format(ErrorMessage.NOT_EXISTING_ENTITY_WITH_NAME,
                                User.class.getSimpleName(),
                                username)));
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.
                        format(ErrorMessage.NOT_EXISTING_ENTITY_WITH_ID,
                                User.class.getSimpleName(),
                                id)));
    }

    @Override
    public User createUser(@Valid User user) {
        try {
            this.getUserByUsername(user.getUsername());
            throw new IllegalArgumentException(String
                    .format(ErrorMessage.ENTITY_WITH_NAME_ALREADY_EXISTS,
                            User.class.getSimpleName(),
                            user.getUsername()));

        } catch (EntityNotFoundException enfe) {
            user.setCreated(LocalDateTime.now());
            user.setModified(LocalDateTime.now());

            HashSet<Role> roles = null;
            if (user.getRoles() == null || user.getRoles().size() == 0) {
                roles = new HashSet<>();
                roles.add(Role.BUYER);
                user.setRoles(roles);
            }

            if (this.userRepository.count() == 0) {
                user.getRoles().add(Role.ADMIN);
            }

            return this.userRepository.saveAndFlush(user);
        }
    }

    @Override
    public User updateUser(@Valid User user) {
        //Throws exception if entity does not exists(invalid id)
        User userById = this.getUserById(user.getId());

        if (user.getUsername() != null && !user.getUsername().equals(userById.getUsername())){
            throw new IllegalArgumentException(ErrorMessage.USERNAME_CANNOT_BE_CHANGED);
        }

        user.setModified(LocalDateTime.now());

        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public User deleteUser(Long id) {
        //Throws exception if user with that id does not exists!
        User userById = this.getUserById(id);

        this.userRepository.deleteById(id);

        return userById;
    }

    @Override
    public Long getUsersCount() {
        return this.userRepository.count();
    }
}
