package org.pharma.cofa.services;

import org.pharma.cofa.entities.Demande;
import org.pharma.cofa.repositories.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface EmailService {
    String sendSimpleMail(String details);


public long sendMail(Demande mail) ;


}
