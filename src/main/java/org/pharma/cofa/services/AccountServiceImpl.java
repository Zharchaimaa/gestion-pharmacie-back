package org.pharma.cofa.services;

import lombok.AllArgsConstructor;
import org.pharma.cofa.entities.EntityPersonne;
import org.pharma.cofa.repositories.PersonneRepository;
import org.pharma.cofa.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{
    @Autowired
    private PersonneRepository personneRepository;
    @Override
    public EntityPersonne addUser(EntityPersonne appUser) {
        return personneRepository.save(appUser);
    }
    @Override
    public EntityPersonne updateUser(@RequestBody EntityPersonne user, @PathVariable(name = "matricule") String cne) {

           /* EntityPersonne existingEMP = personneRepository.findByMatricule(cne);//orElse(null)
            if (existingEMP == null) {
                System.out.println("cet employe n'existe pas !");
            } else {
                existingEMP.setMatricule(user.getMatricule());
                existingEMP.setNom(user.getNom());
                existingEMP.setPrenom(user.getPrenom());
                existingEMP.setEntite(user.getEntite());
                existingEMP.setProfil(user.getProfil());
                /*String p = user.getPassword();*/
                /*existingEMP.setPassword(user.getPassword());
                personneRepository.save(existingEMP);
            }
            return existingEMP;*/
        user.setMatricule(cne);
        return personneRepository.save(user);
        }

    @Override
    public List<EntityPersonne> listUsers() {
         // récupérer les informations d'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EntityPersonne user = (EntityPersonne) authentication.getPrincipal();

        // véréfier si utilisateur a le droit sur la liste
        if(user.getProfil().equals(Constants.Profil.admin))
            return  personneRepository.findAll();
        else
            return null;
        //return  personneRepository.findAll();
    }
    @Override
    public void deleteUser(String cne) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EntityPersonne user = (EntityPersonne) authentication.getPrincipal();

        // véréfier si utilisateur a le droit sur la liste
        if(user.getProfil().equals(Constants.Profil.admin))
            personneRepository.deleteByMatricule(cne);
    }
    @Override
    public EntityPersonne getUser(String cne) {
       /* Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EntityPersonne user = (EntityPersonne) authentication.getPrincipal();

        // véréfier si utilisateur a le droit sur la liste
        if(user.getProfil().equals(Constants.Profil.admin))
            return personneRepository.findByMatricule(cne);
        else
            return null;*/
        return personneRepository.findByMatricule(cne);

    }

    @Override
    public EntityPersonne loadUserByNom(String nom) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EntityPersonne user = (EntityPersonne) authentication.getPrincipal();

        // véréfier si utilisateur a le droit sur la liste
        if(user.getProfil().equals(Constants.Profil.admin))
            return personneRepository.findByNom(nom);
        else
            return null;

    }

}
