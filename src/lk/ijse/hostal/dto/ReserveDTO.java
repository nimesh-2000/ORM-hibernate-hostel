package lk.ijse.hostal.dto;

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
    String studentId;
    String roomId;
    String status;
}
