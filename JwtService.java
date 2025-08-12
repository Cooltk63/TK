package com.example.loginservice.filter;

import com.example.loginservice.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtService {

    private final String SECRET_KEY="thisIsTheMostSecretKeytcs123springframeworkspringframeworkspringframework";
    private final long ACCESS_TOKEN_EXPIRATION_TIME=1000 * 60 *15;// 15 minutes
    private final long REFRESH_TOKEN_EXPIRATION_TIME=1000* 60 * 60 * 24 *1; // 1 Day

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    public Map<String, Object> setClaims(User user,Map<String,Object> userRoleInfo) {
        Map<String,Object> claims=new HashMap<>();
        claims.put("userId",user.getUserId());
        claims.put("firstName",user.getFirstName());
        claims.put("middleName",user.getMiddleName());
        claims.put("lastName",user.getLastName());
        claims.put("email",user.getEmail());
        claims.put("phoneNumber",user.getPhoneNumber());
        claims.put("role",userRoleInfo.get("role"));
        claims.put("roleName",userRoleInfo.get("roleName"));
        return claims;
    }
    public String generateAccessToken(User user,Map<String,Object> userRoleInfo) {

        Map<String,Object> claims= setClaims(user,userRoleInfo);


        return Jwts.builder().claims(claims).setSubject(String.valueOf(user.getUserId())).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS256   ,getSecretKey()).compact();

    }

    public String generateRefreshToken(User user,Map<String,Object> userRoleInfo) {

        Map<String,Object> claims= setClaims(user,userRoleInfo);


        return Jwts.builder().claims(claims).setSubject(String.valueOf(user.getUserId())).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS256   ,getSecretKey()).compact();

    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    public boolean validateAccessToken(String token, int userId) {
        int tokenUserId = extractUserId(token);
        return userId == tokenUserId && !isTokenExpired(token);
    }
    public boolean isTokenExpired(String token) {
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }
    public int extractUserId (String token) {
        return Integer.parseInt(extractClaim(token,Claims::getSubject));
    }
}
