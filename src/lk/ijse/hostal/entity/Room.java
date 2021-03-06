package lk.ijse.hostal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "room")
public class Room {
    @Id
    String room_type_id;
    @Column(nullable = false)
    String type;
    @Column(nullable = false)
    BigDecimal key_money;
    @Column(nullable = false)
    int qty;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    List<Reserve> roomList;

    public Room(String room_type_id, String type, BigDecimal key_money, int qty) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
    }
}
