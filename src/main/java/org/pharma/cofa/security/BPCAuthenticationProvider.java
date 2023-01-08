package org.pharma.cofa.security;

import org.pharma.cofa.entities.EntityPersonne;
import org.pharma.cofa.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Authentication Provider
 *
 * @Author: OUMEIMA ZINAOUI
 * @Version: 1.0
 * <p>
 * Fournisseur d'authentification
 */
@Component
public class BPCAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthService authService; // injecter le service d'authentication

    /**
     * vérifier d'authentification
     *
     * @param authentication l'objet de demande d'authentification
     * @return authentification spring security
     * @throws AuthenticationException qui a causé l'exception
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EntityPersonne user = this.authService.checkUser(authentication.getName(), authentication.getCredentials().toString());
        if (user != null)
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        else
            return null;
    }

    /**
     * vérifier si authentification est une instance de class de UsernamePasswordAuthenticationToken
     *
     * @param authentication l'objet de demande d'authentification
     * @return supporter ?
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}