package ru.eds2809.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eds2809.model.User;
import ru.eds2809.repository.interfaces.RoleRepository;
import ru.eds2809.repository.interfaces.UserRepository;
import ru.eds2809.service.interfaces.UserService;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

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

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = getUserByLogin(login);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPass(), user.getRoles());
    }
}
