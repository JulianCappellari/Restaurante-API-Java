package pp.restaurante.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pp.restaurante.entities.Table;

public interface TableRepository extends JpaRepository<Table, Long> {
}
