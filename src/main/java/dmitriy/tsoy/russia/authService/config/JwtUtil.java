package dmitriy.tsoy.russia.authService.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Log
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String login) {
        Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        String token = Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        log.info("jwt = " + token);
        return token;
    }

    public boolean checkToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        Date expiration = claims.getExpiration();
        Date currentDate = new Date();
        return !expiration.before(currentDate);
    }
}
