package Enigma.ParkingProject.controller;

import Enigma.ParkingProject.service.WindowsNotification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/notifaction")
public class NotificationControllerTest {

    @PostMapping()
    public void SendNotifcation() throws AWTException {
        if (SystemTray.isSupported()) {
            WindowsNotification wn = new WindowsNotification();
            wn.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
    }

}
