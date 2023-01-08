package org.pharma.cofa.services;

import org.pharma.cofa.entities.EntityMedicament;
import org.pharma.cofa.entities.EntityVente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VenteService {
    EntityVente addVente(EntityMedicament medicament, Long code);
    EntityVente getVente(Long id);
    List<EntityVente> listVente();
    List<EntityVente> listVenteByUser(EntityVente vente);
}
/*@Service
public interface VenteService {
    EntityVente addVente(EntityVente vente);
    EntityVente getVente(Long id);
    List<EntityVente> listVente();

    List<EntityVente> listVenteByUser(EntityVente vente);
}
*/