package org.github.scalabletaskmanager.common.service;

import io.jsonwebtoken.Claims;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    /**
     * Extracts the username from the provided JWT token.
     *
     * @param token the JWT token from which the username is to be extracted
     * @return the extracted username as a string
     */
    String extractUsername(String token);

    /**
     * Extracts a specific claim from the provided JWT token.
     *
     * @param <T> the type of the claim to be extracted
     * @param token the JWT token from which the claim is to be extracted
     * @param claimsResolver a function that specifies how to extract the desired claim from the token's claims
     * @return the extracted claim
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Generates a JWT token for the specified user with additional claims.
     *
     * @param extraClaims a map containing additional claims to be included in the token
     * @param username the username for which the token is to be generated
     * @return the generated JWT token as a string
     */
    String generateToken(Map<String, Object> extraClaims, String username);

    /**
     * Checks if JWT token is expired or not.
     *
     * @param token to validate
     * @return if it's expired or not
     */
    boolean isTokenExpired(String token);
}
