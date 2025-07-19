package omar.HyperCell.intern.task2.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import omar.HyperCell.intern.task2.model.dto.AuthUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${app.security.jwt.secret-key}")
    private String secret;
    @Value("${app.security.jwt.expiration}")
    private long jwtExpiration;


    public String generateToken(AuthUser authUser) {
        return generateToken(new HashMap<>() {{
//            put("id", authUser.getId());
//            put("name", authUser.getName());
            put("authorities", authUser.getAuthorities());
        }}, authUser);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails authUser) {
        final String username = extractUsername(token);
        return (username.equals(authUser.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails authUser) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(authUser.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(Instant.now().plus(8, ChronoUnit.HOURS).toEpochMilli()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
