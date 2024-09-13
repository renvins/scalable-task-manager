package org.github.scalabletaskmanager.common;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

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
     * Generates a JWT token for the given user with additional claims.
     *
     * @param extraClaims additional claims to be included in the token
     * @param userDetails the user details for whom the token is being generated
     * @return the generated JWT token as a string
     */
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    /**
     * Checks whether the provided JWT token is valid for the given user details.
     *
     * @param token       the JWT token to validate
     * @param userDetails the user details to validate against
     * @return true if the token is valid, false otherwise
     */
    boolean isTokenValid(String token, UserDetails userDetails);
}
