package pp.restaurante.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pp.restaurante.config.JwtUtil;
import pp.restaurante.dto.UserDto;

import pp.restaurante.entities.User;

import pp.restaurante.respositories.UserRepository;
import pp.restaurante.services.user.UserService;
import pp.restaurante.transformers.UserMapper;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UserControllers {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserControllers(UserService userService, UserMapper userMapper, JwtUtil jwtTokenProvider, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.jwtTokenProvider = jwtTokenProvider; // Inyecta el proveedor de tokens
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        try {
            UserDto registeredUser = userService.register(userDto);  // El servicio devuelve un UserDto
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        Optional<UserDto> user = userService.login(userDto.getEmail(), userDto.getPassword());
        if (user.isPresent()) {
            String token = jwtTokenProvider.generateToken(user.get()); // Método para generar JWT
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getById(id);
        return user.map( u -> ResponseEntity.ok(userMapper.toDto(u))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/usuarios/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User userToUpdate = userMapper.toEntity(userDto);
        User userUpdated = userService.update(userToUpdate, id);
        return userUpdated != null ? ResponseEntity.ok(userMapper.toDto(userUpdated)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
