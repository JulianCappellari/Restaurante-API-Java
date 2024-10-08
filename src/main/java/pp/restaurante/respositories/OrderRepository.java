package pp.restaurante.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pp.restaurante.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
