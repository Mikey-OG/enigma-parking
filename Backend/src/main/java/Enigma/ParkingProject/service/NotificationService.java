package Enigma.ParkingProject.service;

import java.awt.*;

public class NotificationService {
    public void displayTray() throws AWTException {

        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Guest Arrived!", "The guest ... has arrived for his appointment at ...", TrayIcon.MessageType.INFO);
    }
}
