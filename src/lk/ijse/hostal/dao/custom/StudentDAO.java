package lk.ijse.hostal.dao.custom;

import lk.ijse.hostal.dao.CrudDAO;
import lk.ijse.hostal.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student,String> {

//     List<Student> searchStudent(String enteredText) throws SQLException, ClassNotFoundException;
}
