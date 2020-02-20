package ru.eds2809.repository.interfaces;

import ru.eds2809.model.Role;

import java.util.List;

public interface RoleRepository {
    Role findByRole(String role);

    List<Role> getAll();
}
