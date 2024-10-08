package pp.restaurante.transformers;

import org.springframework.stereotype.Component;
import pp.restaurante.dto.UserDto;
import pp.restaurante.entities.User;
import pp.restaurante.enums.Role;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId()); // Asegúrate de incluir el ID
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole().name()); // Solo un rol
        System.out.println("User Dto: " + dto); // Agregar punto de interrupción aquí
        return dto;
    }

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId()); // También asigna el ID aquí
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        // Solo un rol
        if (dto.getRole() != null) {
            user.setRole(Role.valueOf(dto.getRole().toUpperCase())); // Asegúrate de que el rol esté en mayúsculas.
        }

        return user;
    }
}
