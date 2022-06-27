package lk.ijse.hostal.bo.custom;

import lk.ijse.hostal.bo.SuperBO;
import lk.ijse.hostal.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentBO extends SuperBO {
    List<StudentDTO> getAllStudent() throws Exception;

    boolean saveStudent(StudentDTO dto) throws Exception;

    boolean updateStudent(StudentDTO dto) throws Exception;


    boolean deleteStudent(String id) throws Exception;


}
