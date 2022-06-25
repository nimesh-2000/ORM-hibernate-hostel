package lk.ijse.hostal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    String room_type_id;
    String type;
    String key_money;
    int qty;
}
