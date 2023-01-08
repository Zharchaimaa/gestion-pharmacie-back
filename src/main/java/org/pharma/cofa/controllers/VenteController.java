package org.pharma.cofa.controllers;

import org.pharma.cofa.entities.EntityMedicament;
import org.pharma.cofa.entities.EntityVente;
import org.pharma.cofa.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class VenteController {
    @Autowired
    private VenteService venteService;

    @PostMapping(path = "/addVentes/{code}")
    EntityVente ajouterVente(@RequestBody EntityMedicament medicament, @PathVariable(name = "code") Long code){
        return venteService.addVente(medicament,code);
    }
    @GetMapping(path = "/getVente")
    public EntityVente getVente(Long id) {
        return venteService.getVente(id);

    }

    @GetMapping(path = "/listeVente")
    public List<EntityVente> listVente() {
        return venteService.listVente();
    }

    @GetMapping(path = "/listVenteByUser")
    public List<EntityVente> listVenteByUser(EntityVente vente) {
        return venteService.listVenteByUser(vente);
    }
}