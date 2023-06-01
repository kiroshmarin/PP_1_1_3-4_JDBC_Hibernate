package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User(\n" +
                    "  id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,\n" +
                    "  name VARCHAR(45),\n" +
                    "  lastName VARCHAR(45),\n" +
                    "  age TINYINT\n" +
                    ");").executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.save(new User(name, lastName, age));

            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.remove(session.get(User.class, id));

            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            userList =  session.createQuery("FROM User")
                    .getResultList();

            session.getTransaction().commit();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.createQuery("DELETE FROM User").executeUpdate();

            session.getTransaction().commit();
        }
    }
}
