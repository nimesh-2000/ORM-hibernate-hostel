package lk.ijse.hostal.dto;

import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDTO {
    String resId;
    LocalDate date;
    Student studentId;
    Room roomId;
    String status;
    int qty;
}
