package pp.restaurante.services.inventory;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pp.restaurante.entities.Inventory;
import pp.restaurante.respositories.InventoryRepository;
import pp.restaurante.transformers.InventoryMapper;

import java.util.List;
import java.util.Optional;


@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;
    private InventoryMapper inventoryMapper;

    @Override
    public Inventory add(Inventory entity) {
        return inventoryRepository.save(entity);
    }

    @Override
    public Optional<Inventory> getById(Long id) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid inventory id: " + id));
        return inventoryRepository.findById(id);
    }

    @Override
    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if(inventoryRepository.findById(id).isPresent()) {
            inventoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Inventory update(Inventory entity, Long id) {
        return inventoryRepository.findById(id).map( inventory -> {
            inventory.setName(inventory.getName());
            inventory.setId(inventory.getId());
            inventory.setAmount(inventory.getAmount());
            inventory.setMinimumThreshold(inventory.getMinimumThreshold());
            return inventoryRepository.save(inventory);
        }).orElse(null);
    }
}
