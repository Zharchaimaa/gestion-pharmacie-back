package org.pharma.cofa.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity

public class EntityMedicament {
    @Id
    private  Long code;
    private String libelle;
    private  Double prixUnitaire;
    private String description;
    private Long stock;
}
