package lk.ijse.hostal.dao.custom.impl;

import lk.ijse.hostal.dao.CrudDAO;
import lk.ijse.hostal.dao.custom.StudentDAO;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<Student> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "FROM Student";
        Query query=session.createQuery(hql);

        List<Student>studentList = query.getResultList();
        return studentList;

    }

    @Override
    public boolean save(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public boolean update(Student entity) throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, id);

        transaction.commit();
        session.close();
        return student;
    }


    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }


    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.load(Student.class, id);

        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String generateNewID() throws Exception {
       return null;
        }


    @Override
    public List<Student> searchStudent(String enteredText) throws SQLException, ClassNotFoundException {
        return null;
    }
}
