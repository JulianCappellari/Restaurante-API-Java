package pp.restaurante.services.order;

import org.springframework.stereotype.Service;
import pp.restaurante.entities.Order;
import pp.restaurante.respositories.OrderRepository;
import pp.restaurante.transformers.OrderMapper;

import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order add(Order entity) {
        return orderRepository.save(entity);
    }

    @Override
    public Optional<Order> getById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalStateException("Order not found"));
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        };
        return false;
    }

    @Override
    public Order update(Order entity, Long id) {
        return orderRepository.findById(id).map(order -> {
            order.setId(order.getId());
            order.setCustomerId(order.getCustomerId());
            order.setOrderState(order.getOrderState());
            order.setDate(order.getDate());
            order.setTotalAmount(order.getTotalAmount());
            return orderRepository.save(order);
        }).orElse(null);
    }
}
