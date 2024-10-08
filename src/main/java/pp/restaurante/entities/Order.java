package pp.restaurante.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pp.restaurante.enums.OrderStateEnum;

import java.util.Date;

@Entity(name = "Pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customerId")
    private Long customerId;

    @Column(name = "date")
    private Date date;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private OrderStateEnum orderState;

    @Column(name = "totalAmount")
    private float totalAmount;


}
