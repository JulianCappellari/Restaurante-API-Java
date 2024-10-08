package pp.restaurante.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pp.restaurante.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
