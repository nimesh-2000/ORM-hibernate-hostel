package lk.ijse.hostal.dao.custom;

import lk.ijse.hostal.dao.CrudDAO;
import lk.ijse.hostal.entity.Reserve;

import java.util.List;

public interface ReserveDAO extends CrudDAO<Reserve,String> {

    List<Reserve> searchReserve(String enteredText)throws Exception;
}
