package lk.ijse.hostal.bo.custom;

import lk.ijse.hostal.bo.SuperBO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
    List<UserDTO> getAllUser() throws Exception;

    boolean saveUser(UserDTO dto) throws Exception;

    boolean updateUser(UserDTO dto) throws Exception;


    boolean deleteUser(String id) throws Exception;
}
