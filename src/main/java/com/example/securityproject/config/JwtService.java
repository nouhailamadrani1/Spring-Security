package com.example.securityproject.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private String secret_key = "+jig8CUe374HVXEDiChIyg0TCaCgbqMhVEA2nmelSZWWMcHAipNTxMZicxsBaHBJg+VSdWacw0QI1l2gPrIkl/ChnxgDhwDCAqPD1IGP+cfKi8r8xzpRGSikvjU+J2q6aGeaN5p0JPgn8y/Z/+2ywqqGfAhcSCtMQOPBAfvjkG4YI+68pxqm4BvlIN8VGdXlgONv8R1P0EmAw+qBidJaEBBmd17x0cUCNYBnMvjMuyQOchvkiE2xEv6bYDxTtdxXJXxrPPwL8L7T1LffmQPpGsXNqItxjCq675jZ93C5odNSjpc25ET0GGoYOM4jiUPkQ4faQVZDI0zt37tdoWwPM8Y6XJwmhqvbVH+U0FLoAbc=";
    public String extractUsername(String jwtToken){
       return extractClaim(jwtToken, Claims::getSubject);
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver){
       final Claims claims = extractAllClaims(jwtToken);
       return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String jwtToken){
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(jwtToken).getBody();
    }

    public Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
       return  Jwts.builder()
               .setClaims(extraClaims)
               .setSubject(userDetails.getUsername())
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
               .signWith(getSigningKey(), SignatureAlgorithm.HS256)
               .compact();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public Boolean isTokenValid(String jwtToken, UserDetails userDetails){
        final String username = extractUsername(jwtToken);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    public boolean isTokenExpired(String jwtToken) {
        Date expirationDate = extractClaim(jwtToken, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

}
