package lk.ijse.hostal.dao.custom;

import lk.ijse.hostal.dao.CrudDAO;
import lk.ijse.hostal.entity.User;

public interface UserDAO extends CrudDAO<User,String> {
    User searchUser(String id) throws Exception;
}
