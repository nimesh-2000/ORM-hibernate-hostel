package lk.ijse.hostal.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTM {
    String room_type_id;
    String type;
    BigDecimal key_money;
    int qty;
}
