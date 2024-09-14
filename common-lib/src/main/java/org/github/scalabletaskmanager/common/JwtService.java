package org.github.scalabletaskmanager.common;

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
     * Validates the given JWT token by comparing the extracted username with the provided username.
     *
     * @param token the JWT token to be validated
     * @param comparedUsername the username to compare against the username extracted from the token
     * @return true if the token is valid and the usernames match, false otherwise
     */
    boolean isTokenValid(String token, String comparedUsername);
}
