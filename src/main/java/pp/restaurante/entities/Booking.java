package pp.restaurante.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity(name = "Booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Booking {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customerId")
    private Long customerId;

    @Column(name = "bookingDate")
    private Date bookingDate;

    @Column(name = "numberPeople")
    private Integer numberPeople;
}
