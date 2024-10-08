package pp.restaurante.transformers;

import org.springframework.stereotype.Component;
import pp.restaurante.dto.InventoryDto;
import pp.restaurante.entities.Inventory;


@Component
public class InventoryMapper {

    public InventoryDto toDto(Inventory inventory) {
        return new InventoryDto(
                inventory.getId(),
                inventory.getName(),
                inventory.getAmount(),
                inventory.getMinimumThreshold()
        );
    }

    public Inventory toEntity(InventoryDto inventoryDto) {
        return new Inventory(
                inventoryDto.getId(),
                inventoryDto.getName(),
                inventoryDto.getAmount(),
                inventoryDto.getMinimumThreshold()
        );
    }
}
