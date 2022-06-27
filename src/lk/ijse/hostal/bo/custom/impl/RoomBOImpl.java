package lk.ijse.hostal.bo.custom.impl;

import lk.ijse.hostal.bo.custom.RoomBO;
import lk.ijse.hostal.dao.DAOFactory;
import lk.ijse.hostal.dao.custom.RoomDAO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);


    @Override
    public boolean saveRoom(RoomDTO dto) throws Exception {
        return roomDAO.save(new Room(dto.getRoom_type_id(),dto.getType(),dto.getKey_money(),dto.getQty()));

    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws Exception {
        return roomDAO.update(new Room(dto.getRoom_type_id(),dto.getType(),dto.getKey_money(),dto.getQty()));

    }

    @Override
    public boolean deleteRoom(String id) throws Exception {
        return roomDAO.delete(id);

    }

    @Override
    public List<RoomDTO> getAllRoom() throws Exception {
        List<Room> all = roomDAO.getAll();
        List<RoomDTO> allDTO = new ArrayList<>();
        for (Room s : all){
            allDTO.add(new RoomDTO(s.getRoom_type_id(),s.getType(),s.getKey_money(),s.getQty()));
        }
        return allDTO;
    }



}
