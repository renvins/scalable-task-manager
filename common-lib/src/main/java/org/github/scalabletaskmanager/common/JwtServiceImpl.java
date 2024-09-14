package org.github.scalabletaskmanager.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public class JwtServiceImpl implements JwtService {

    private static final String SECRET = "tKxNDTlrMAXpw+zFJpHW9CC4/z5bGrtzhhe196krPnhtiDqUwT41GsHsVSpp0JERfz8+P2H1Mw7lr9PqNCc7tLhl64YKKmmwDZgn0rZ4LpTISTbsOTO70XCK6V8IIWgU4Nwfo6fgCS36Yy1zVdWvtTsESMEuFrrcMnNhjgNq2qYw1Eennd56sA+TNPSwby4ZDDzrJLcSUL7jZramTUrZbxJpDK+TkgiuMk8DQI2fWeU0WNuL2NdApN2/CwvOa8fdLOOBZq8ZzL1p7F7o1ot1tqZai10KFMhJkA0s5dDK/Uvc5k2SKzw0FipdIftOiI//ANJBuubOkLxxoCzUwUUzZPO5qaB11tabMhXvoAka1/o=";

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, String username) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 60))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, String comparedUsername) {
        String username = extractUsername(token);
        return username.equals(comparedUsername) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes); // Using JWT's preferred algorithm
    }
}
