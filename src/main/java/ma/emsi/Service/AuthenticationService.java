package ma.emsi.Service;

import ma.emsi.Model.AuthenticationResponse;
import ma.emsi.Model.User;
import ma.emsi.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepo repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;


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
         user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user=repository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token, user.getId());
    }
    public AuthenticationResponse Authenticate(User request) {
        // Retrieve the user by username
        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Check if the password matches
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user);


        return new AuthenticationResponse(token, user.getId());
    }
}
