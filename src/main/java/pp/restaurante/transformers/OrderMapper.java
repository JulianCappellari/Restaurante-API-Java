package pp.restaurante.transformers;

import org.springframework.stereotype.Component;
import pp.restaurante.dto.OrderDto;
import pp.restaurante.entities.Order;


@Component
public class OrderMapper {

    public OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCustomerId(),
                order.getDate(),
                order.getOrderState(),
                order.getTotalAmount()
        );
    }

    public Order toEntity(OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getCustomerId(),
                orderDto.getDate(),
                orderDto.getOrderState(),
                orderDto.getTotalAmount()
        );
    }
}
