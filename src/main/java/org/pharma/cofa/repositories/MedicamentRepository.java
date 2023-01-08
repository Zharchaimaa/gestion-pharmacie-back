package org.pharma.cofa.repositories;

import org.pharma.cofa.entities.EntityMedicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentRepository extends JpaRepository<EntityMedicament,Long> {
    EntityMedicament findByLibelle(String libelle);

    EntityMedicament findByCode(Long code);
}
