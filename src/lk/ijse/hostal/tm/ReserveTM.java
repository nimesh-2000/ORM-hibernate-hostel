package lk.ijse.hostal.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveTM {
        String reservationId;
        String studentId;
        String roomId;
        LocalDate date;
        String status;
        int qty;

}
