package biz.touchtechnologies.backendchallanege.config.security;

import biz.touchtechnologies.backendchallanege.application.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider  {

        @Value("${jwt.secret}")
        private String secret;

        @Value("${jwt.expiration}")
        private long validityInMilliseconds;

        @PostConstruct
        public void init(){
            secret = Base64.getEncoder().encodeToString(secret.getBytes());
        }

        public String createToken(User user) {
            Map<String, Object> claims = Jwts.claims().setSubject(user.getUsername());
            claims.put("id", user.getId());


            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + validityInMilliseconds);

            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
        }

        public boolean validateToken(String token) {
            try {
                Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public String getUsernameFromToken(String token) {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return claims.getSubject();
        }

}
