package lk.ijse.hostal.dao;

import lk.ijse.hostal.bo.custom.impl.RoomBOImpl;
import lk.ijse.hostal.dao.custom.impl.ReserveDAOImpl;
import lk.ijse.hostal.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostal.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        STUDENT,ROOM,RESERVE
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVE:
                return new ReserveDAOImpl();
            default:
                return null;
        }
    }
}