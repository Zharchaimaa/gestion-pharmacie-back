package org.pharma.cofa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email_citoyen")
    @Email(message = "Please enter a valid email adress!")
    @NotEmpty(message = "Please provide an email! This field can not be empty!")
    private String email;

    private String name;
    private String subject;

    @Column(name = "nom_citoyen")
    private String nom;

    @Column(name = "prenom_citoyen")
    @NotEmpty(message = "Please provide your Lastname! This field can not be empty!")
    private String prenom;

    @Column(name = "cin")
    @NotEmpty(message = "Please provide your firstname! This field can not be empty!")
    private String CIN;

   /* @ManyToOne
    private Procedure procedure;*/
}
