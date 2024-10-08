package pp.restaurante.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.restaurante.dto.OrderDto;
import pp.restaurante.entities.Order;
import pp.restaurante.services.order.OrderService;
import pp.restaurante.transformers.OrderMapper;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class OrderControllers {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    // Inyección de dependencias a través del constructor
    public OrderControllers(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order orderToSave = orderService.add(order);
        return ResponseEntity.ok(orderMapper.toDto(orderToSave));
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderService.getAll();
        return orders.stream().map(orderMapper::toDto).toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order orderUpdated = orderService.update(order, id);
        return orderUpdated != null ? ResponseEntity.ok(orderMapper.toDto(orderUpdated)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public boolean deleteOrder(@PathVariable Long id) {
        if(orderService.getById(id).isPresent()) {
            orderService.delete(id);
            return true;
        }
        return false;
    }

}
