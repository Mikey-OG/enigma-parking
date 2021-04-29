package Enigma.ParkingProject.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointmentId")
    private int appointmentId;
    @Column(name = "guestId")
    private int guestId;
    @Column(name = "emailEmployee")
    private String employeeEmail;
    @Column(name = "appointmentDate")
    private String appointmentDate;

    public Appointment() {
    }

    public Appointment(int appointmentId, int guestId, String employeeEmail, String appointmentDate) {
        this.appointmentId = appointmentId;
        this.guestId = guestId;
        this.employeeEmail = employeeEmail;
        this.appointmentDate = appointmentDate;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
