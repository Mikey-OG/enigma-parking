package Enigma.ParkingProject.service;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class WhatsappService {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC6416fb83cd021d68e6017edaf49af703";
    public static final String AUTH_TOKEN = "323b9d7e30c2d55993487ae90e48bc44";

    public void WhatsappParkingFull() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("whatsapp:+31684570643"),
                new PhoneNumber("whatsapp:+14155238886"),
                "Hello , welcome to Sioux! The parking at Sioux is full.")
                .create();

        System.out.println(message.getSid());
    }

    public void WhatsappParkingAvailable() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("whatsapp:+31684570643"),
                new PhoneNumber("whatsapp:+14155238886"),
                "Hello, welcome to Sioux! You can park at Sioux parking.")
                .create();

        System.out.println(message.getSid());
    }
}
