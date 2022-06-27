package lk.ijse.hostal.bo.custom.impl;

import lk.ijse.hostal.bo.custom.RoomBO;
import lk.ijse.hostal.dto.RoomDTO;

import java.sql.SQLException;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    @Override
    public List<RoomDTO> getAllRoom() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveRoom(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }



    @Override
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        return false;
    }




}
