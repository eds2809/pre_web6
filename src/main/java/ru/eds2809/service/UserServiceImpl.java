package ru.eds2809.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eds2809.intarfaces.RoleRepository;
import ru.eds2809.intarfaces.UserRepository;
import ru.eds2809.intarfaces.UserService;
import ru.eds2809.model.User;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(@Qualifier("hibernateEntityManagerRepository") UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean save(String login, String pass, String email, String role) {
        if (login.isEmpty() || pass.isEmpty() || role.isEmpty()){
            return false;
        }

        return userRepository.save(
                new User(
                        login,
                        passwordEncoder.encode(pass),
                        email,
                        Collections.singleton(
                                roleRepository.findByRole(role)
                        )
                )
        );
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getByID(Long id) {
        return userRepository.getByID(id);
    }

    @Override
    public boolean deleteByID(Long id) {
        return userRepository.deleteByID(id);
    }

    @Override
    public boolean updateUser(Long id, String login, String pass, String email, String role) {

        User user = getByID(id);

        if (!login.isEmpty()){
            user.setLogin(login);
        }

        if (!pass.isEmpty()){
            user.setPass(passwordEncoder.encode(pass));
        }

        if (!role.isEmpty()){
            user.setRoles(
                    Collections.singleton(
                            roleRepository.findByRole(role)
                    )
            );
        }

        return userRepository.updateUser(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }
}
