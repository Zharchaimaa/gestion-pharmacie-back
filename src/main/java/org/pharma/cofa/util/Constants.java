package org.pharma.cofa.util;

/**
 * Constants
 *
 * @Author: OUMEIMA ZINAOUI
 * @Version: 1.0
 */

public interface Constants {

    String[] periodes = {"annuel", "mensuel", "trimestriel"};

    interface SecurityScheme {
        String name = "auth";
        String format = "JWT";
        String scheme = "bearer";
    }

    interface Claims {
        String matricule = "Matricule";
        String profil = "Profil";
        String entite = "Entite";
    }

    interface Profil {
        String admin = "admin";
        String employe = "EMPLOYE";
    }

}
