package omar.HyperCell.intern.task2.config;

import lombok.RequiredArgsConstructor;
import omar.HyperCell.intern.task2.model.db.User;
import omar.HyperCell.intern.task2.model.dto.AuthUser;
import omar.HyperCell.intern.task2.repositry.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public String authenticate(AuthenticationDto authDTO) {
        Authentication auth = new UsernamePasswordAuthenticationToken(authDTO.username(), authDTO.password());
        authenticationManager.authenticate(auth);
        User user = userRepository.findByUserName(authDTO.username()).get();
        return jwtService.generateToken(new AuthUser(user));
    }

    public String register(RegisterDto registerDto) {
        //        validate each input field alone is not null
        if (registerDto.firstname() == null || registerDto.firstname().isEmpty() ) {
            throw new IllegalArgumentException("Fist Name is null or empty");
        }
        if (registerDto.lastname() == null || registerDto.lastname().isEmpty()) {
            throw new IllegalArgumentException("Last Name is null or empty");
        }
        if (registerDto.username() == null || registerDto.username().isEmpty()) {
            throw new IllegalArgumentException("User Name is null or empty");
        }
        if (registerDto.password() == null || registerDto.password().isEmpty()) {
            throw new IllegalArgumentException("Password is null or empty");
        }
        if (registerDto.role() == null) {
            throw new IllegalArgumentException("Role is null");
        }


        User user = new User();
        user.setUserName(registerDto.username());
        user.setFirstName(registerDto.firstname());
        user.setLastName(registerDto.lastname());
        user.setRole(registerDto.role());

        user.setPassword(registerDto.password());
        // email from username + @gmail.com
        user.setEmail(registerDto.username() + "@gmail.com");
        userRepository.save(user);
        return jwtService.generateToken(new AuthUser(user));
    }
}
