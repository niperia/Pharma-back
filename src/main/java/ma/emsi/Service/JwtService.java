package ma.emsi.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import ma.emsi.Model.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private String Secret_key="1471f4c4e248d260f622c9b6e22f199651cf4f1a5a7906ebacb82948a07f7766";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public boolean isValid(String token, User user) {
        String username=extractUsername(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> Resolver) {
        Claims claims=extractAllClaims(token);
        return Resolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String generateToken(User user) {
        String token= Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .signWith(getSigningKey())
                .compact();
        return token;
    }
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(Secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
