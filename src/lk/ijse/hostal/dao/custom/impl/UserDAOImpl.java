package lk.ijse.hostal.dao.custom.impl;

import lk.ijse.hostal.dao.custom.UserDAO;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.entity.User;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "FROM user";
        Query query=session.createQuery(hql);

        List<User>userList = query.getResultList();
        return userList;

    }

    @Override
    public boolean save(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public boolean update(User entity) throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        String hql = "FROM user WHERE userId = :userId";
        Query query = session.createQuery(hql);
        query.setParameter("userId",id);
        List list = query.getResultList();
        return list;
    }



    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = session.load(User.class, id);

        session.delete(user);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User searchUser(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        User user = session.find(User.class, id);

        session.close();
        return user;
    }
}
