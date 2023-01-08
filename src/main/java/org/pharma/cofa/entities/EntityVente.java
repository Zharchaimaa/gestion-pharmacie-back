package org.pharma.cofa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class EntityVente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long codeOperation;
    private  String libelle;
    private  Double prixUnitaire;
    private  String description;
    private  Long stock;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "idUserEnCharge")
    private EntityPersonne user;
}
