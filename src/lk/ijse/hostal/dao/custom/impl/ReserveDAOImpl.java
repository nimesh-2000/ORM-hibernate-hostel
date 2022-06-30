package lk.ijse.hostal.dao.custom.impl;

import lk.ijse.hostal.dao.custom.ReserveDAO;
import lk.ijse.hostal.entity.Reserve;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class ReserveDAOImpl implements ReserveDAO {

    @Override
    public boolean save(Reserve dto) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        Reserve reserve = session.load(Reserve.class, s);

        session.delete(reserve);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reserve dto) throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List search(String s) throws Exception{
        return null;
    }

    @Override
    public List<Reserve> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "FROM Reserve";
        Query query = session.createQuery(hql);
        List<Reserve> reserveList = query.getResultList();

        return reserveList;
    }

    @Override
    public List<Reserve> searchReserve(String enteredText) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        String hql = "FROM Reserve WHERE res_id = :res_Id";
        Query query = session.createQuery(hql);
        query.setParameter("res_Id", enteredText);
        List list = query.getResultList();
        return list;
    }

    @Override
    public String getReservationId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<String> list = session.createQuery("SELECT res_id FROM Reserve ORDER BY res_id DESC").setMaxResults(1).list();
        transaction.commit();
        session.close();
        return list.size()>0? String.format("RES-%03d", (Integer.parseInt(list.get(0).replace("RES-", "")) + 1)) : "RES-001";


    }

    @Override
    public List<Reserve> remainKeyMoneyStudents() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Reserve WHERE status LIKE '%Not Paid'";
        List<Reserve> remainKeyMoneyStudentsList = session.createQuery(hql).list();

        transaction.commit();
        session.close();
        return remainKeyMoneyStudentsList;
    }
}
