package ru.eds2809.service.interfaces;


import ru.eds2809.model.User;

import java.util.List;

public interface UserService{

    boolean save(String login, String pass, String email, String role);

    List<User> getAll();

    User getByID(Long id);

    boolean deleteByID(Long id);

    boolean updateUser(Long id, String login, String pass, String email, String role);

    User getUserByLogin(String login);
}
