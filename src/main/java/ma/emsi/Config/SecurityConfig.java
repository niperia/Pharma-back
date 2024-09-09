package ma.emsi.Config;

import jakarta.servlet.Filter;
import ma.emsi.Filter.JwtAuthenticationFilter;
import ma.emsi.Model.Enum.Role;
import ma.emsi.Service.UserDetailsImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsImp userDetailsImp, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                                req->req.requestMatchers("/**")

                                        .permitAll()
                                        .requestMatchers("/admin/**").hasAuthority(String.valueOf(Role.ADMIN))
                                        .anyRequest()

                                        .authenticated()
                )
                .userDetailsService(userDetailsImp)
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore( jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration Configuration) throws Exception {
        return Configuration.getAuthenticationManager();
    }
}
