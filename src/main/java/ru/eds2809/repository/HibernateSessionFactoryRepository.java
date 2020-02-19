package ru.eds2809.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.eds2809.intarfaces.UserRepository;
import ru.eds2809.model.User;

import javax.persistence.Query;
import java.util.List;

@Repository
public class HibernateSessionFactoryRepository implements UserRepository {

    private final SessionFactory sessionFactory;

    public HibernateSessionFactoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean save(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        long id = (long) session.save(user);
        transaction.commit();
        session.close();
        return id > 0;
    }

    @Override
    public boolean delete(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<User> getAll() {
        Session session = getSession();
        Query query = session.createQuery("from User");
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    @Override
    public User getByID(Long id) {
        Session session = getSession();
        Query query = session.createQuery(
                "from User where  id=:id"
        );
        query.setParameter("id", id);
        User user = (User) query.getSingleResult();
        session.close();
        return user;
    }

    @Override
    public boolean deleteByID(Long id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }
}
