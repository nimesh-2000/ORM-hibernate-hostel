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
    public ArrayList<StudentDTO> getAllStudent() throws Exception {
        List<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudent= new ArrayList<>();
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
    public boolean StudentExist(String id) throws Exception {
        return studentDAO.exist(id);
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public String generateNewStudentID() throws Exception {
        return studentDAO.generateNewID();
    }

    @Override
    public ArrayList<StudentDTO> searchStudent(String enteredText) throws SQLException, ClassNotFoundException {
        List<Student> students = studentDAO.searchStudent(enteredText);
        ArrayList<StudentDTO> studentDto=new ArrayList<>();

        for (Student student : students) {
            studentDto.add(new StudentDTO(student.getStudent_id(),
                    student.getStudentName(),
                    student.getStudentAddress(),
                    student.getContact_no(),
                    student.getDob(),
                    student.getGender()
            ));
        }
        return studentDto;
    }
}
