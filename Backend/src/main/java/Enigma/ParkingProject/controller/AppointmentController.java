package Enigma.ParkingProject.controller;

import Enigma.ParkingProject.model.Appointment;
import Enigma.ParkingProject.serviceinterfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @GetMapping //GET at http://localhost:XXXX/appointments
    public ResponseEntity<List<Appointment>> getAllAppointments()
    {
        List<Appointment> appointments;
        appointments = appointmentService.getAllAppointments();
        if(appointments != null) {
            return ResponseEntity.ok().body(appointments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment newAppointment)
    {
        appointmentService.addAppointment(newAppointment);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}") //DELETE at http://localhost:XXXX/appointments/id
    public ResponseEntity deleteAppointment(@PathVariable int id)
    {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }

}
