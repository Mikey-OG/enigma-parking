package Enigma.ParkingProject.service;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class Whatsapp {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "input your account sid";
    public static final String AUTH_TOKEN = "Input your Authentication token";

    public void WhatsappParkingFull() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("whatsapp:+31684570643"),
                new PhoneNumber("whatsapp:+14155238886"),
                "Hello {name}, welcome to Sioux! The parking at Sioux is full. But you can to try to park at {place}.")
                .create();

        System.out.println(message.getSid());
    }

    public void WhatsappParkingAvailable() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("whatsapp:+31684570643"),
                new PhoneNumber("whatsapp:+14155238886"),
                "Hello {name}, welcome to Sioux! You can park at Sioux parking.")
                .create();

        System.out.println(message.getSid());
    }
}
