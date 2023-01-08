package org.pharma.cofa.repositories;

import org.pharma.cofa.entities.EntityPersonne;
import org.pharma.cofa.entities.EntityVente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenteRepository extends JpaRepository<EntityVente,Long> {

    List<EntityVente> findByUser(EntityPersonne user);

    EntityVente findByCodeOperation(Long id);
}
