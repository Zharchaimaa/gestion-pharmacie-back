package org.pharma.cofa.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.pharma.cofa.entities.AuthenticationRequestDto;
import org.pharma.cofa.entities.EntityPersonne;
import org.pharma.cofa.entities.JwtResponseDto;
import org.pharma.cofa.services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController
 *
 * @Author: OUMEIMA ZINAOUI
 * @Version: 1.0
 * <p>
 * Controleur d'authentification
 */

@Validated
@RestController
@Tag(name = "Authentification", description = "Authentification JWT")
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping(value = "/auth")

    public ResponseEntity<JwtResponseDto> authenticate(
           @RequestBody AuthenticationRequestDto authenticationRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.matricule, authenticationRequest.profil);
        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        EntityPersonne user = (EntityPersonne) authentication.getPrincipal();
        final String token = jwtTokenService.generateToken(user);
        return new ResponseEntity<>(new JwtResponseDto(user.getMatricule(), user.getProfil(), token), HttpStatus.OK);
    }

}

