package pp.restaurante.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.restaurante.dto.InventoryDto;
import pp.restaurante.entities.Inventory;
import pp.restaurante.services.inventory.InventoryService;
import pp.restaurante.transformers.InventoryMapper;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventoryController {


    private InventoryService inventoryService;


    private InventoryMapper inventoryMapper;

    public InventoryController(InventoryService inventoryService, InventoryMapper inventoryMapper) {
        this.inventoryService = inventoryService;
        this.inventoryMapper = inventoryMapper;
    }

    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto) {
        Inventory inventory = inventoryMapper.toEntity(inventoryDto);
        Inventory inventoryToSave = inventoryService.add(inventory);
        return ResponseEntity.ok(inventoryMapper.toDto(inventoryToSave));
    }

    @GetMapping
    public List<InventoryDto> getAllInventory() {
        List<Inventory> inventory = inventoryService.getAll();
        return inventory.stream().map(inventoryMapper::toDto).toList();
    }

    @PutMapping("/{id}")
    public InventoryDto updateInventory(@PathVariable Long id, @RequestBody InventoryDto inventoryDto) {
        Inventory inventory = inventoryMapper.toEntity(inventoryDto);
        Inventory inventoryToUpdate = inventoryService.update(inventory, id);
        return inventoryToUpdate != null ? inventoryMapper.toDto(inventoryToUpdate) : null;
    }

    @DeleteMapping("/{id}")
    public boolean deleteInventory(@PathVariable Long id) {
        if (inventoryService.getById(id).isPresent()) {
            inventoryService.delete(id);
            return true;
        }
        return false;
    }
}
