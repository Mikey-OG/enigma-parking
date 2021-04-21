package Enigma.ParkingProject.service;

import Enigma.ParkingProject.model.Appointment;
import Enigma.ParkingProject.repository.IAccountDAL;
import Enigma.ParkingProject.repository.IAppointmentDAL;
import Enigma.ParkingProject.serviceinterfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    IAppointmentDAL appointmentDAL;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentDAL.getAllAppointments();
    }

    @Override
    public Appointment getAppointmentById(int id) {
        return appointmentDAL.getAppointmentById(id);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentDAL.addAppointment(appointment);
    }

    @Override
    public void deleteAppointment(int id) {
        appointmentDAL.deleteAppointment(id);
    }
}
