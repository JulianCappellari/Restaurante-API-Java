package pp.restaurante.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.restaurante.dto.MenuDto;
import pp.restaurante.entities.Menu;
import pp.restaurante.services.menu.MenuService;
import pp.restaurante.transformers.MenuMapper;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private MenuService menuService;
    private MenuMapper  menuMapper;
    public MenuController(MenuService menuService, MenuMapper menuMapper) {
        this.menuService = menuService;
        this.menuMapper = menuMapper;
    }

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto) {
        Menu menu = menuMapper.toEntity(menuDto);
        Menu menuToSave = menuService.add(menu);
        return ResponseEntity.ok(menuMapper.toDto(menuToSave));
    }

    @GetMapping
    public List<MenuDto> getAllMenus() {
        List<Menu> menus = menuService.getAll();
        return  menus.stream().map(menuMapper::toDto).toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDto> updateMenu(@PathVariable Long id, @RequestBody MenuDto menuDto) {
        Menu menu = menuMapper.toEntity(menuDto);
        Menu menuUpdated = menuService.update(menu, id);
        return menuUpdated != null ? ResponseEntity.ok(menuMapper.toDto(menuUpdated)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public boolean deleteMenu(@PathVariable Long id) {
        if(menuService.getById(id).isPresent()) {
            menuService.delete(id);
            return true;
        }
        return false;
    }
}
