package lk.ijse.hostal.bo.custom.impl;

import lk.ijse.hostal.bo.custom.StudentBO;
import lk.ijse.hostal.dao.DAOFactory;
import lk.ijse.hostal.dao.custom.StudentDAO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<StudentDTO> getAllStudent() throws Exception {
        List<Student> all = studentDAO.getAll();
        List<StudentDTO> allStudent= new ArrayList<>();
        for (Student student : all) {
            allStudent.add(new StudentDTO(student.getStudent_id(),student.getStudentName(),student.getStudentAddress(),student.getContact_no()
                    ,student.getDob(),student.getGender()));
        }
        return allStudent;
    }


    @Override
    public boolean saveStudent(StudentDTO dto) throws Exception {
        return studentDAO.save(new Student(dto.getStudent_id(),dto.getStudentName(),dto.getStudentAddress(),dto.getContact_no(),
                dto.getDob(),dto.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws Exception {
        return studentDAO.update(new Student(dto.getStudent_id(),dto.getStudentName(),dto.getStudentAddress(),dto.getContact_no(),
                dto.getDob(),dto.getGender()));
    }



    @Override
    public boolean deleteStudent(String id) throws Exception {
        return studentDAO.delete(id);
    }




}
