package pp.restaurante.services.user;

import pp.restaurante.entities.User;
import pp.restaurante.dto.UserDto;
import pp.restaurante.services.Service;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service<User, Long> {
    UserDto register(UserDto userDto);
    Optional<UserDto> login(String email, String password);
    Optional<User> findByEmail(String email);
}
