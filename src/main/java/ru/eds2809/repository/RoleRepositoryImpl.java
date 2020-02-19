package ru.eds2809.repository;

import org.springframework.stereotype.Repository;
import ru.eds2809.intarfaces.RoleRepository;
import ru.eds2809.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findByRole(String role) {
        Query query = entityManager.createQuery("select r from Role r where role=:role");
        query.setParameter("role", role);
        return (Role) query.getSingleResult();
    }

    @Override
    public List<Role> getAll() {
        Query query = entityManager.createQuery("select r from  Role r");
        return query.getResultList();
    }

}
