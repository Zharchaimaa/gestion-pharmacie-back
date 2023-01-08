package org.pharma.cofa.security;


import org.pharma.cofa.entities.EntityPersonne;
import org.pharma.cofa.services.AuthService;
import org.pharma.cofa.services.JwtTokenService;
import org.pharma.cofa.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.Entity;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Request Filter
 *
 * @Author: OUMEIMA ZINAOUI
 * @Version: 1.0
 * <p>
 * Validation du jeton d'identité JWT
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService; // injecter le service JwtToken

    @Autowired
    private AuthService authService; // injecter le service d'authentication

    /**
     * vérifier le jeton d'identité JWT
     *
     * @param request     requête
     * @param response    réponse
     * @param filterChain lae chaîne de filtre (spring security)
     * @throws ServletException qui a causé l'exception
     * @throws IOException      IO exception
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // données de jeton d'identité JWT
        String matricule = null;
        String profil = null;
        String jwtToken = null;

        // extraction de données (matricule, profil token) depuis le jeton d'identité JWT
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.toLowerCase().startsWith(Constants.SecurityScheme.scheme.toLowerCase())) {
            jwtToken = token.substring(7);
            matricule = jwtTokenService.getAttributeFromToken(jwtToken, Constants.Claims.matricule);
            profil = jwtTokenService.getAttributeFromToken(jwtToken, Constants.Claims.profil);
        }

        // vérifier le jeton d'identité JWT
        if (matricule != null && profil != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            EntityPersonne user = authService.checkUser(matricule, profil);
            if (jwtTokenService.validateToken(jwtToken, user)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getMatricule(), user.getProfil());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // enchainer vers le prochain filtre
        filterChain.doFilter(request, response);
    }
}
