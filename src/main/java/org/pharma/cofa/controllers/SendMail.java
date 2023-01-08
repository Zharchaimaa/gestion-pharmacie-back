package org.pharma.cofa.controllers;

import org.pharma.cofa.entities.Demande;

import org.pharma.cofa.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/sendEmail")
@Controller
public class SendMail {

    @Autowired
    private JavaMailSender sender;


    @Autowired private EmailService emailService;


  @PostMapping("/getMails/{email}")
    public String
    sendMails(@PathVariable(name = "email") String email)
    {
        String status
                = emailService.sendSimpleMail(email);

        return status;
    }

    @GetMapping ("/getdetails/{email}")
    public @ResponseBody String sendMail(@RequestBody Demande notification,@PathVariable(name = "email") String email) throws Exception {
//        EntityPersonne e = new EntityPersonne();
        //String mail = notification.getEmail();
       // System.out.println(mail);
String generateJeton = notification.getCIN().concat(String.valueOf(Math.random())).concat(String.valueOf(notification.getId()));
        /*MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsmailets.UTF_8.name());*/
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        //message.setTo("zrwalid22@gmail.com");
        message.setSubject("Notification!");
        message.setText("il s'agit du jeton pour suivre vote demande"+generateJeton);

        sender.send(message);


        //sender.send(message);

     /*   try {
            helper.setTo(notification.getEmailAddress());

            helper.setSubject("Notification!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }*/
       // sender.send(message);
        return "mail";
    }
}
