package Enigma.ParkingProject.repository;

import Enigma.ParkingProject.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<Appointment, Integer> {
    Appointment getAppointmentByAppointmentId(int id);
}
