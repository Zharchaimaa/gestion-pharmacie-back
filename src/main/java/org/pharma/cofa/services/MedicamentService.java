package org.pharma.cofa.services;

import org.pharma.cofa.entities.EntityMedicament;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicamentService {
    public EntityMedicament addMedicament(EntityMedicament Medicament);
    public EntityMedicament updateMedicament(Long id, EntityMedicament Medicament);
    public EntityMedicament findMedicament(Long id);
    public EntityMedicament findMedicamentLibelle(String libelle);
    public List<EntityMedicament> listMedicament();
    public void deleteMedicament(Long id);
}
