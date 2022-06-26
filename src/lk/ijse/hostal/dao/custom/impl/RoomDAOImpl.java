package lk.ijse.hostal.dao.custom.impl;

import lk.ijse.hostal.dao.custom.RoomDAO;
import lk.ijse.hostal.entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public List<Room> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(Room dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(Room dto) throws Exception {
        return false;
    }

    @Override
    public Room search(String s) throws Exception {
        return null;
    }

    @Override
    public boolean exist(String s) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public String generateNewID() throws Exception {
        return null;
    }

    @Override
    public ArrayList<Room> searchRoom(String enteredText) throws SQLException, ClassNotFoundException {
        return null;
    }
}
