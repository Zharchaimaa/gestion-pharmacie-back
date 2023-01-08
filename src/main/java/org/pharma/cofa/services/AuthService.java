package org.pharma.cofa.services;

import org.pharma.cofa.entities.EntityPersonne;

/**
 * Auth Service
 *
 * @Author: OUMEIMA ZINAOUI
 * @Version: 1.0
 */


public interface AuthService {

    EntityPersonne checkUser(String matricule, String profil);
}
