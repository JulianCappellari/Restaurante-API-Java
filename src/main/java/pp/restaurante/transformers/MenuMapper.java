package pp.restaurante.transformers;

import org.springframework.stereotype.Component;
import pp.restaurante.dto.MenuDto;
import pp.restaurante.entities.Menu;


@Component
public class MenuMapper {

    public MenuDto toDto(Menu menu) {
        return new MenuDto(
                menu.getId(),
                menu.getNameDish(),
                menu.getPrice(),
                menu.isAvailable()
        );
    }
    public Menu toEntity(MenuDto menuDto) {
        return new Menu(
                menuDto.getId(),
                menuDto.getNameDish(),
                menuDto.getPrice(),
                menuDto.isAvailable()
        );
    }
}
