package dmitriy.tsoy.russia.authService.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Log
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String login) {
        Date date = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));
        String token = Jwts.builder()
                .setIssuer("autfffService")
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        log.info("jwt = " + token);
        return token;
    }

    public boolean checkToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        log.info("claims = " + claims);
        String iss = claims.getIssuer();
        Date expiration = claims.getExpiration();
        Date currentDate = new Date();
        return (!expiration.before(currentDate) && (iss.equals("autfffService")));
    }
}
