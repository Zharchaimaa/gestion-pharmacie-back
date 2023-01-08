package org.pharma.cofa.services;

import lombok.AllArgsConstructor;
import org.pharma.cofa.entities.EntityMedicament;
import org.pharma.cofa.repositories.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicamentServiceImpl implements MedicamentService {
    @Autowired
    MedicamentRepository medicamentRepository;

    @Override
    public EntityMedicament addMedicament(EntityMedicament medicament) {

        return medicamentRepository.save(medicament);
    }

    @Override
    public EntityMedicament updateMedicament(Long code, EntityMedicament medicament) {
        medicament.setCode(code);
        return medicamentRepository.save(medicament);
    }

    @Override
    public EntityMedicament findMedicament(Long id) {
        return medicamentRepository.findById(id).get();
    }

    @Override
    public EntityMedicament findMedicamentLibelle(String libelle) {
        return medicamentRepository.findByLibelle(libelle);
    }

    @Override
    public List<EntityMedicament> listMedicament() {
        return medicamentRepository.findAll();
    }

    @Override
    public void deleteMedicament(Long id) {
        medicamentRepository.deleteById(id);

    }
}
