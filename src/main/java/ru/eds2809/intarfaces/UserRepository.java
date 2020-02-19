package ru.eds2809.intarfaces;

import ru.eds2809.model.User;

import java.util.List;

public interface UserRepository {
    boolean save(User user);

    boolean delete(User user);

    List<User> getAll();

    User getByID(Long id);

    boolean deleteByID(Long id);

    boolean updateUser(User user);

    User getUserByLogin(String login);
}
