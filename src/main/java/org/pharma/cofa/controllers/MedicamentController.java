package org.pharma.cofa.controllers;

import org.pharma.cofa.entities.EntityMedicament;
import org.pharma.cofa.services.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class MedicamentController {
    @Autowired
    private MedicamentService medicamentService;
    @GetMapping(path = "/MedById/{code}")
    EntityMedicament getMedicament(@PathVariable(name = "code") Long id){
        return medicamentService.findMedicament(id);
    }
    @GetMapping(path = "/medicaments")
    List<EntityMedicament> listMedicament(){
        return medicamentService.listMedicament();
    }

    @PostMapping(path = "/addMed")
    EntityMedicament addMedicament(@RequestBody EntityMedicament medicament){
        return medicamentService.addMedicament(medicament);
    }
    @PutMapping(path = "/updateM/{id}")
    EntityMedicament updateMedicament(@RequestBody EntityMedicament Medicament, @PathVariable(name="id")Long id){
        return medicamentService.updateMedicament(id,Medicament);
    }
    @DeleteMapping(path = "/medicament/{code}")
    void deleteMedicament(@PathVariable(name="code")Long id ){
        medicamentService.deleteMedicament(id);
    }

}
