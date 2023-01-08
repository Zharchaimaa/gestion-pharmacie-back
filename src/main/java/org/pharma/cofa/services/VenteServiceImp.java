package org.pharma.cofa.services;


import lombok.AllArgsConstructor;
import org.pharma.cofa.entities.EntityMedicament;
import org.pharma.cofa.entities.EntityPersonne;
import org.pharma.cofa.entities.EntityVente;
import org.pharma.cofa.repositories.MedicamentRepository;
import org.pharma.cofa.repositories.PersonneRepository;
import org.pharma.cofa.repositories.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
@AllArgsConstructor
public class VenteServiceImp implements VenteService {

    @Autowired
    private VenteRepository venteRepository;
    @Autowired
    private MedicamentRepository medicamentRepository;


    @Override
    public EntityVente addVente(@RequestBody EntityMedicament medicament, @PathVariable(name = "code") Long code) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EntityPersonne user = (EntityPersonne) authentication.getPrincipal();
        EntityMedicament existingEMP = medicamentRepository.findByCode(code);//orElse(null)

        EntityVente nouv = new EntityVente();
        if (existingEMP == null) {
            System.out.print("cet employée n'existe pas");
            return null;
        } else {
            nouv.setLibelle(existingEMP.getLibelle());
            nouv.setStock(1L);
            nouv.setDescription(existingEMP.getDescription());
            nouv.setPrixUnitaire(existingEMP.getPrixUnitaire());
            nouv.setUser(user);
            existingEMP.setStock(medicament.getStock() - 1);
            medicamentRepository.save(existingEMP);
            return venteRepository.save(nouv);
        }
    }



    @Override
    public EntityVente getVente(Long id) {
        return venteRepository.findByCodeOperation(id);
    }

    @Override
    public List<EntityVente> listVente() {
        return venteRepository.findAll();
    }

    @Override
    public List<EntityVente> listVenteByUser(EntityVente vente) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EntityPersonne user = (EntityPersonne) authentication.getPrincipal();
        if(user.getMatricule().equals(vente.getUser()))
        {
            return venteRepository.findByUser(vente.getUser());
        }
        return null;
    }
}

/*@Service
public class VenteServiceImp implements VenteService {

    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private VenteRepository venteRepository;

    @Override
    public EntityVente addVente(EntityVente vente) {
        return venteRepository.save(vente);
    }

    @Override
    public EntityVente getVente(Long id) {
        return null;
    }

    @Override
    public List<EntityVente> listVente() {
        return venteRepository.findAll();
    }

    @Override
    public List<EntityVente> listVenteByUser(EntityVente vente) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EntityPersonne user = (EntityPersonne) authentication.getPrincipal();

        if(user.getMatricule().equals(vente.getUser()))
        {
            return venteRepository.findByUser(vente.getUser());
        }
        return null;
    }
}
*/