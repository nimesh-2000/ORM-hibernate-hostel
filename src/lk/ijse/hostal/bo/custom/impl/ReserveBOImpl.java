package lk.ijse.hostal.bo.custom.impl;

import lk.ijse.hostal.bo.custom.ReserveBO;
import lk.ijse.hostal.dao.DAOFactory;
import lk.ijse.hostal.dao.custom.ReserveDAO;
import lk.ijse.hostal.dao.custom.RoomDAO;
import lk.ijse.hostal.dao.custom.StudentDAO;
import lk.ijse.hostal.dto.ReserveDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.entity.Reserve;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ReserveBOImpl implements ReserveBO {
    private final ReserveDAO reserveDAO = (ReserveDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVE);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);


    @Override
    public boolean save(ReserveDTO dto) throws Exception{
        return reserveDAO.save(new Reserve(dto.getResId(),dto.getDate(), dto.getStudentId(), dto.getRoomId(), dto.getStatus(), dto.getQty()));
    }

    @Override
    public List<ReserveDTO> getAllReserve() throws Exception {
        List<Reserve> all = reserveDAO.getAll();
        List<ReserveDTO> allDTO = new ArrayList<>();
        for (Reserve r : all){
            allDTO.add(new ReserveDTO(r.getRes_id(),r.getDate(),r.getStudent(),r.getRoom(),r.getStatus(),r.getQty()));
        }
        return allDTO;
    }

    @Override
    public List<ReserveDTO> searchReserve(String enteredText) throws Exception {
        List<Reserve> reserves = reserveDAO.searchReserve(enteredText);
        List<ReserveDTO> reserveDTOS = new ArrayList<>();
        for (Reserve rs : reserves){
            reserveDTOS.add(new ReserveDTO(rs.getRes_id(), rs.getDate(), rs.getStudent(), rs.getRoom(), rs.getStatus(), rs.getQty()));
        }
        return reserveDTOS;
    }

    @Override
    public List<StudentDTO> searchStudent(String id) throws Exception {
        List<Student> student = studentDAO.search(id);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student st : student){
            studentDTOS.add(new StudentDTO(st.getStudent_id(), st.getStudentName(), st.getStudentAddress(), st.getContact_no(), st.getDob(), st.getGender()));
        }
        return studentDTOS;
    }

    @Override
    public List<RoomDTO> searchRoom(String id) throws Exception {
        List<Room> room = roomDAO.search(id);
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room r : room){
            roomDTOS.add(new RoomDTO(r.getRoom_type_id(),r.getType(),r.getKey_money(),r.getQty()));
        }
        return roomDTOS;
    }

    @Override
    public List<StudentDTO> getAllStudent() throws Exception {
        List<Student> all = studentDAO.getAll();
        List<StudentDTO> allDTO = new ArrayList<>();
        for (Student s : all){
            allDTO.add(new StudentDTO(s.getStudent_id(),s.getStudentName(),s.getStudentAddress(),s.getContact_no(),s.getDob(),s.getGender()));
        }
        return allDTO;
    }

    @Override
    public List<RoomDTO> getAllRoom() throws  Exception {
        List<Room> all = roomDAO.getAll();
        List<RoomDTO> allDTO = new ArrayList<>();
        for (Room r : all){
            allDTO.add(new RoomDTO(r.getRoom_type_id(),r.getType(),r.getKey_money(),r.getQty()));
        }
        return allDTO;
    }

    @Override
    public boolean deleteReservation(String id) throws Exception {
        return reserveDAO.delete(id);
    }

    @Override
    public boolean UpdateReservation(ReserveDTO dto) throws Exception {
        return reserveDAO.update(new Reserve(
                dto.getResId(),
                dto.getDate(),
                dto.getStudentId(),
                dto.getRoomId(),
                dto.getStatus(),
                dto.getQty()
        ));
    }

    @Override
    public String getReservationId() throws Exception {
        return reserveDAO.getReservationId();
    }

    @Override
    public List<ReserveDTO> remainKeyMoneyStudents() throws Exception {
        List<Reserve> reservationList = reserveDAO.remainKeyMoneyStudents();
        List<ReserveDTO>reservationDTOS = new ArrayList<>();
        for (Reserve reservation:reservationList) {
            reservationDTOS.add(new ReserveDTO(reservation.getRes_id(),reservation.getDate(),reservation.getStudent(),reservation.getRoom(),reservation.getStatus(),reservation.getQty()));
        }
        return reservationDTOS;
    }

}
