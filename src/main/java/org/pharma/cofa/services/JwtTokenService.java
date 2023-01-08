package org.pharma.cofa.services;


import io.jsonwebtoken.Claims;
import org.pharma.cofa.entities.EntityPersonne;

import java.util.Date;
import java.util.function.Function;

/**
 * Token Service
 *
 * @Author: OUMEIMA ZINAOUI
 * @Version: 1.0
 */

public interface JwtTokenService {

    String getSubjectFromToken(String token);

    String getAttributeFromToken(String token, String attribute);

    Date getIssuedAtDateFromToken(String token);

    Date getExpirationDateFromToken(String token);

    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);

    String generateToken(EntityPersonne user);

    Boolean canTokenBeRefreshed(String token);

    Boolean validateToken(String token, EntityPersonne user);
}
