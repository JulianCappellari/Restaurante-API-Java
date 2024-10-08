package pp.restaurante.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pp.restaurante.dto.UserDto;
import pp.restaurante.entities.User;

import pp.restaurante.enums.Role;
import pp.restaurante.respositories.UserRepository;
import pp.restaurante.transformers.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto register(UserDto userDto) {
        User user = userMapper.toEntity(userDto);  // Mapea UserDto a User
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.valueOf(userDto.getRole().toUpperCase()));  // Asegura que el rol esté en mayúsculas
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);  // Mapea User a UserDto
    }

    @Override
    public Optional<UserDto> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            // Convertir User a UserDto
            UserDto userDto = userMapper.toDto(userOptional.get());
            return Optional.of(userDto);
        }
        return Optional.empty();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User add(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id); // Devolver directamente el Optional
    }


    @Override
    public boolean delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User update(User entity, Long id) {
        return userRepository.findById(id).map( user -> {
            user.setId(user.getId());
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setEmail(user.getEmail());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(user.getRole());
            return userRepository.save(user);
        }).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
