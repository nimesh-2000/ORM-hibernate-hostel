package lk.ijse.hostal.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTM {
    String studentId;
    String studentName;
    String address;
    String ContactNo;
    String dob;
    String gender;
}
