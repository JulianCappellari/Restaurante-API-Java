package pp.restaurante.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pp.restaurante.entities.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
