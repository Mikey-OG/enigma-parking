package Enigma.ParkingProject.service;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class Sms {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "input your Twilio account sid";
    public static final String AUTH_TOKEN = "Input your authentication token";

    public void SendSmsParkingFull(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+31655289268"),
                "MGfdded448aafea6c48572679dd93fa642",
                "Hello {name}, welcome to Sioux! The parking at Sioux is full. But you can to try to park at {place}.")
                .create();

        System.out.println(message.getSid());
    }

    public void SendSmsParkingAvailable(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+31655289268"),
                "MGfdded448aafea6c48572679dd93fa642",
                "Hello {name}, welcome to Sioux! You can park at Sioux parking.")
                .create();

        System.out.println(message.getSid());
    }
}
