package pp.restaurante.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import pp.restaurante.dto.UserDto;
import pp.restaurante.entities.CustomUserDetails;
import pp.restaurante.services.user.UserService;
import pp.restaurante.services.user.CustomUserDetailsService;
import pp.restaurante.transformers.UserMapper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            UserDto registeredUser = userService.register(userDto);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            logger.error("Error al registrar el usuario: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error al registrar el usuario.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        try {
            // Autenticar al usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

            // Cargar los detalles del usuario desde la base de datos
            CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(userDto.getEmail());

            // Convertir CustomUser Details a UserDto
            UserDto userDtoFromDetails = userMapper.toDto(userDetails.getUser());

            // Generar el token JWT utilizando el UserDto
            String jwt = jwtUtil.generateToken(userDtoFromDetails);

            // Retornar el JWT como respuesta
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            logger.error("Error de autenticación para el usuario {}: {}", userDto.getEmail(), e.getMessage());
            return ResponseEntity.badRequest().body("Error de autenticación.");
        }
    }
}
