package ru.eds2809.repository;

import org.springframework.stereotype.Repository;
import ru.eds2809.intarfaces.UserRepository;
import ru.eds2809.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class HibernateEntityManagerRepository implements UserRepository {

    @PersistenceContext
    EntityManager em;


    @Override
    public boolean save(User user) {
        em.persist(user);
        return true;
    }

    @Override
    public boolean delete(User user) {
        em.detach(user);
        em.remove(user);
        return true;
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("Select u from User u").getResultList();
    }

    @Override
    public User getByID(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public boolean deleteByID(Long id) {
        User u = getByID(id);
        em.detach(u);
        em.remove(u);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        em.detach(user);
        em.merge(user);
        return true;
    }

    @Override
    public User getUserByLogin(String login) {
        Query query = em.createQuery("from User where login=:login");
        query.setParameter("login",login);
        return (User) query.getSingleResult();
    }
}
