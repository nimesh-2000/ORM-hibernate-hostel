package lk.ijse.hostal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    String student_id;
    String studentName;
    String studentAddress;
    String contact_no;
    String dob;
    String gender;
}
