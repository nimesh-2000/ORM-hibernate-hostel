package lk.ijse.hostal.bo.custom;

import lk.ijse.hostal.bo.SuperBO;
import lk.ijse.hostal.dto.ReserveDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.dto.StudentDTO;

import java.util.List;

public interface ReserveBO extends SuperBO {

    boolean save(ReserveDTO dto) throws Exception;
    List<ReserveDTO> getAllReserve() throws Exception;
    List<ReserveDTO> searchReserve(String enteredText) throws Exception;
    List<StudentDTO> searchStudent (String id) throws Exception;
    List<RoomDTO> searchRoom (String id) throws Exception;
    List<StudentDTO> getAllStudent() throws Exception;
    List<RoomDTO> getAllRoom() throws Exception;
    boolean deleteReservation(String  id) throws Exception;
    boolean UpdateReservation(ReserveDTO dto) throws Exception;
    String getReservationId() throws Exception;


}
