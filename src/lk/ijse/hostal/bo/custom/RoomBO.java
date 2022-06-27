package lk.ijse.hostal.bo.custom;

import lk.ijse.hostal.bo.SuperBO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.dto.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {

    List<RoomDTO> getAllRoom() throws SQLException, ClassNotFoundException;

    boolean saveRoom(RoomDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateRoom(RoomDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteRoom(String id) throws SQLException, ClassNotFoundException;

}
