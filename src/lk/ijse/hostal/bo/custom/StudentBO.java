package lk.ijse.hostal.bo.custom;

import lk.ijse.hostal.bo.SuperBO;
import lk.ijse.hostal.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentBO extends SuperBO {
    List<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException;

    boolean saveStudent(StudentDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException;

    boolean StudentExist(String id) throws SQLException, ClassNotFoundException;

    boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;

    String generateNewStudentID() throws SQLException, ClassNotFoundException;

    List<StudentDTO> searchStudent(String enteredText) throws SQLException, ClassNotFoundException;
}
