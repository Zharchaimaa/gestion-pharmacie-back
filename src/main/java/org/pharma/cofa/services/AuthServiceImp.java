package org.pharma.cofa.services;

import org.pharma.cofa.entities.EntityPersonne;
import org.pharma.cofa.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Auth Service Implementation
 *
 * @Author: OUMEIMA ZINAOUI
 * @Version: 1.0
 */
@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    PersonneRepository user1;
    /**
     * Vérifiez les informations d'identification d'utilisateur
     */
    @Override
    public EntityPersonne checkUser(String matricule, String profil)throws UsernameNotFoundException {

        EntityPersonne user = user1.findByMatricule(matricule);
        EntityPersonne user2 = user1.findByProfil(profil);
        return user;
    }
}
