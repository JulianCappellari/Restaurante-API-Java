package pp.restaurante.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pp.restaurante.entities.CustomUserDetails;
import pp.restaurante.entities.User;

import pp.restaurante.respositories.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Manejo de error para el rol
            try {
                return new CustomUserDetails(user);
            } catch (IllegalArgumentException e) {
                // Aquí puedes registrar el error específico del rol
                throw new UsernameNotFoundException("Error al cargar el rol del usuario: " + email + ", " + e.getMessage());
            }
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado: " + email);
        }
    }
}
