package Enigma.ParkingProject.controller;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.model.Appointment;
import Enigma.ParkingProject.service.*;
import Enigma.ParkingProject.serviceinterfaces.IAppointmentService;
import Enigma.ParkingProject.serviceinterfaces.ICSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/scan")
public class ScanController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private LPRService lprService;

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private ICSVService csvService;

    @GetMapping("/LPR")
    public void Scan () throws AWTException {

        NotificationService notificationService = new NotificationService();
        SmsService smsService = new SmsService();
        WhatsappService whatsapp = new WhatsappService();
        String httpcar = "https://www.anwb.nl/binaries/content/gallery/anwb/portal/verzekeringen/autoverzekering/kentekenplaat.jpg/kentekenplaat.jpg/anwb%3Aw760";

       if(lprService.Scan(httpcar) != null) {
           Account account = lprService.ScanAccount(httpcar);
           Appointment appointment = appointmentService.ScanAppointment(lprService.ScanAccount(httpcar).getAccountId());
           List<Appointment> guestsappointments = appointmentService.getAllAppointmentsFromGuest(account.getAccountId());
           if(guestsappointments != null) {
               notificationService.displayTray(account.getFirstName() + " " + account.getLastName(), appointment.getAppointmentStartDate());
           }
           emailService.sendEmail(appointment.getEmployeeEmail(), account.getFirstName()+" "+account.getLastName(), appointment.getAppointmentStartDate());
           boolean available = csvService.assignSpot(account.getAccountId());
           if (available)
           {
               if(account.getContactViaWhatsapp() == false) {
                   smsService.SendSmsParkingAvailable(account.getPhoneNumber(), account.getFirstName() + " " + account.getLastName());
               }
               else {
                   whatsapp.WhatsappParkingAvailable();
               }
           }
           else
           {
               if(account.getContactViaWhatsapp() == false) {
                   smsService.SendSmsParkingFull(account.getPhoneNumber(), account.getFirstName() + " " + account.getLastName());
               }
               else {
                   whatsapp.WhatsappParkingFull();
               }
           }

        }
    }
}
