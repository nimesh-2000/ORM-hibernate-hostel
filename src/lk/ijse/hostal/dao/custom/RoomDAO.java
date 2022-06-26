package lk.ijse.hostal.dao.custom;

import lk.ijse.hostal.dao.CrudDAO;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomDAO extends CrudDAO<Room,String> {
    ArrayList<Room> searchRoom(String enteredText) throws SQLException, ClassNotFoundException;
}
