package org.pharma.cofa.services;

import org.pharma.cofa.entities.EntityPersonne;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    EntityPersonne addUser(EntityPersonne appUser);
    EntityPersonne updateUser(EntityPersonne appUser, String id);
    EntityPersonne loadUserByNom(String username);
    EntityPersonne getUser(String id);
    List<EntityPersonne> listUsers();
    void deleteUser(String username);



}
