package pp.restaurante.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pp.restaurante.enums.OrderStateEnum;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Long customerId;
    private Date date;
    private OrderStateEnum orderState;
    private float totalAmount;
}
