package Enigma.ParkingProject.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String EmailTo) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(EmailTo);
        mail.setFrom("lucmoonen1@gmail.com");
        mail.setSubject("Guest arrived!");
        mail.setText("... has arrived for the appointment at ...");

        javaMailSender.send(mail);
    }
}
