package lk.ijse.hostal.bo;

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
                return new CustomerBOImpl(); //
            case ROOM:
                return new ItemBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        STUDENT,ROOM
    }
}
