package lk.ijse.hostal.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO {
    List<T> getAll() throws Exception;

    boolean save(T dto) throws Exception;

    boolean update(T dto) throws Exception;

    T search(ID id) throws Exception;

    boolean exist(ID id) throws Exception;

    boolean delete(ID id) throws Exception;

    String generateNewID() throws Exception;
}
