package lk.ijse.hostal.bo.custom.impl;

import lk.ijse.hostal.bo.custom.UserBO;
import lk.ijse.hostal.dao.DAOFactory;
import lk.ijse.hostal.dao.custom.StudentDAO;
import lk.ijse.hostal.dao.custom.UserDAO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.dto.UserDTO;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    private final UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public List<UserDTO> getAllUser() throws Exception {
        List<User> all = userDAO.getAll();
        List<UserDTO> allUser= new ArrayList<>();
        for (User user : all) {
            allUser.add(new UserDTO(user.getUserId(),user.getUserName(),user.getPassword()));
        }
        return allUser;
    }

    @Override
    public boolean saveUser(UserDTO dto) throws Exception {
        return userDAO.save(new User(dto.getUserId(),dto.getUserName(),dto.getPassword()));
    }

    @Override
    public boolean updateUser(UserDTO dto) throws Exception {
        return userDAO.update(new User(dto.getUserId(),dto.getUserName(),dto.getPassword()));
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return userDAO.delete(id);
    }
}
