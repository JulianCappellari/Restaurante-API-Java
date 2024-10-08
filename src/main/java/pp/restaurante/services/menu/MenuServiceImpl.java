package pp.restaurante.services.menu;

import org.springframework.stereotype.Service;
import pp.restaurante.entities.Menu;
import pp.restaurante.respositories.MenuRepository;
import pp.restaurante.transformers.MenuMapper;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService{

    private MenuRepository menuRepository;
    private MenuMapper menuMapper;
    public MenuServiceImpl(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }
    @Override
    public Menu add(Menu entity) {
        return menuRepository.save(entity);
    }

    @Override
    public Optional<Menu> getById(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new IllegalStateException("Menu not found"));
        return menuRepository.findById(id);

    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Menu update(Menu entity, Long id) {
        return menuRepository.findById(id).map(menu -> {
            menu.setId(menu.getId());
            menu.setNameDish(menu.getNameDish());
            menu.setPrice(menu.getPrice());
            menu.setAvailable(menu.isAvailable());
            return menuRepository.save(menu);
        }).orElse(null);
    }
}
