package org.pharma.cofa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Mail {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String subject;
    private String emailAddress;
    private String message;
    private String nom;
    private String matricule;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
