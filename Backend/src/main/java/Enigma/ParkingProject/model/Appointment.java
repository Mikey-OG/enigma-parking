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
    @Column(name = "appointmentDate")
    private Date appointmentDate;

}
