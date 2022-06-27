package lk.ijse.hostal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    String room_type_id;
    String type;
    BigDecimal key_money;
    int qty;
}
