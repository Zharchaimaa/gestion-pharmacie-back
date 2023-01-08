package org.pharma.cofa.services;

import org.pharma.cofa.entities.Demande;
import org.pharma.cofa.repositories.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    DemandeRepository demandeRepo;
    @Override
    public long sendMail(Demande mail) throws MailException {
        System.out.println("send mail...");
        String generateJeton = mail.getCIN().concat(String.valueOf(Math.random())).concat(String.valueOf(mail.getId()));
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getEmail());
        msg.setFrom("");
        msg.setSubject("jeton de suivie");
        msg.setText("votre jeton pour le suivie de votre demande " + generateJeton);
        javaMailSender.send(msg);
        return demandeRepo.save(mail).getId();
    }
    @Override
    public String sendSimpleMail(String mail) {
        Demande details = new Demande();
        String generateJeton = details.getCIN().concat(String.valueOf(Math.random())).concat(String.valueOf(details.getId()));
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            //mailMessage.setFrom(sender);
            mailMessage.setTo("oumeima.zinaoui57@gmail.com");
            mailMessage.setText(generateJeton);
            mailMessage.setSubject("details.getSubject()");

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }

    }
}
