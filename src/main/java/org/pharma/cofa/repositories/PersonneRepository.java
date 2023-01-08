package org.pharma.cofa.repositories;

import org.pharma.cofa.entities.EntityPersonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonneRepository extends JpaRepository<EntityPersonne,String> {


    EntityPersonne findByMatricule(String cne);

    void deleteByMatricule(String cne);

    EntityPersonne findByNom(String nom);


    EntityPersonne findByProfil(String profil);
}
