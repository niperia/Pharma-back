package ma.emsi.Service;

import ma.emsi.Model.AuthenticationResponse;
import ma.emsi.Model.User;
import ma.emsi.Repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepo repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepo repository, PasswordEncoder encoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public AuthenticationResponse register(User request) {
        User user =new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        user.setRole(request.getRole());
        user=repository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
    public AuthenticationResponse Authenticate(User request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
         User user =repository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);

    }
}
