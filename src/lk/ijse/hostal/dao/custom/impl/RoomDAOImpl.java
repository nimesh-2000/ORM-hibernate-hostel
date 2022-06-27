package lk.ijse.hostal.dao.custom.impl;

import lk.ijse.hostal.dao.custom.RoomDAO;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean save(Room dto) throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.load(Room.class, s);

        session.delete(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room dto) throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List search(String s) throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();

        String hql = "FROM room WHERE room_type_id = :room_type_Id";
        Query query = session.createQuery(hql);
        query.setParameter("room_type_Id", s);
        List list = query.getResultList();
        return list;
    }

    @Override
    public List<Room> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "FROM room";
        Query query = session.createQuery(hql);
        List<Room> roomList = query.getResultList();

        return roomList;
    }
}
