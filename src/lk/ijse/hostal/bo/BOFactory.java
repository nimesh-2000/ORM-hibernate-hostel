package lk.ijse.hostal.bo;

import lk.ijse.hostal.bo.custom.impl.ReserveBOImpl;
import lk.ijse.hostal.bo.custom.impl.RoomBOImpl;
import lk.ijse.hostal.bo.custom.impl.StudentBOImpl;
import lk.ijse.hostal.bo.custom.impl.UserBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBOImpl(); //
            case ROOM:
                return new RoomBOImpl();
            case RESERVE:
                return new ReserveBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        STUDENT,ROOM,RESERVE,USER
    }
}
