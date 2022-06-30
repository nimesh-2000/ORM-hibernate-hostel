package lk.ijse.hostal.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemainKeyTM {
    String res_Id;
    String date;
    String student_id;
    String room_type_id;
    String status;
}
