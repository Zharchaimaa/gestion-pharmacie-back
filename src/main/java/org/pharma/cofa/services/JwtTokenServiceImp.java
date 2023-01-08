package org.pharma.cofa.services;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.pharma.cofa.entities.EntityPersonne;
import org.pharma.cofa.util.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Token Service Implemetation
 *
 * @Author: OUMEIMA ZINAOUI
 * @Version: 1.0
 */

@Service
public class JwtTokenServiceImp implements JwtTokenService {

    public static final long JWT_TOKEN_VALIDITY = 30 * 60 * 60; // validité token

    @Value("WH\"ogr(mNR0i{#=K:h89i-ktb%<j@Uzl!B7%yp=LUXgns1/K=\"l/{o.<Rwk=V'Z")
    private String secret; // clé de chiffrement symétrique

    /**
     * récuperer le sujet depuis jeton JWT
     *
     * @param token jeton d'identité JWT
     * @return sujet
     */

    public String getSubjectFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * récuperer un attribut depuis jeton JWT
     *
     * @param token     jeton d'identité JWT
     * @param attribute attribut
     * @return information d'attribut
     */

    public String getAttributeFromToken(String token, String attribute) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get(attribute).toString();
    }

    /**
     * récuperer la date de validité depuis jeton JWT
     *
     * @param token jeton d'identité JWT
     * @return date de validité
     */

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    /**
     * récuperer la date d'expiration depuis jeton JWT
     *
     * @param token jeton d'identité JWT
     * @return date d'expiration
     */

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * récuperer le claim depuis jeton JWT
     *
     * @param token          jeton d'identité JWT
     * @param claimsResolver parseur
     * @param <T>            fonction
     * @return claim
     */

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * récuperer tous les claims depuis jeton JWT
     *
     * @param token jeton d'identité JWT
     * @return claims
     */

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * vérifier l'expiration du jeton JWT
     *
     * @param token jeton d'identité JWT
     * @return true/false
     */

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * ignorer l'expiration du jeton JWT
     *
     * @param token jeton d'identité JWT
     * @return true/false
     */
    private Boolean ignoreTokenExpiration(String token) {
        return false;
    }

    /**
     * générer le jeton d'identité JWT
     *
     * @param user modèle d'utilisateur
     * @return jeton d'identité JWT
     */
    public String generateToken(EntityPersonne user) {
        // ajouter matricule, profil et entité d'utilisateur au jeton d'identité JWT
        Map<String, Object> claims = new HashMap<>() {{
            put(Constants.Claims.matricule, user.getMatricule());
            put(Constants.Claims.profil, user.getProfil());
            put(Constants.Claims.entite, user.getEntite());
        }};
        // lancer la génération du jeton d'identité JWT
        return doGenerateToken(claims, user);
    }

    /**
     * générer le jeton d'identité JWT
     *
     * @param claims claim
     * @param user   jeton d'identité JWT
     * @return jeton d'identité JWT
     */
    private String doGenerateToken(Map<String, Object> claims, EntityPersonne user) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getMatricule())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * vérifier l'actualisation du jeton d'identité JWT
     *
     * @param token jeton d'identité JWT
     * @return true/false
     */
    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    /**
     * vérifier la validation du jeton d'identité JWT
     *
     * @param token jeton d'identité JWT
     * @param user  modèle d'utilisateur
     * @return true/false
     */
    public Boolean validateToken(String token, EntityPersonne user) {
        final String matricule = getSubjectFromToken(token);
        return (matricule.equals(user.getMatricule()) && !isTokenExpired(token));
    }

}
