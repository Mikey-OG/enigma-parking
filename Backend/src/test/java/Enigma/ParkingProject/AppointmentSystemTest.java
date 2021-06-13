package Enigma.ParkingProject;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import Enigma.ParkingProject.model.Appointment;
import Enigma.ParkingProject.repository.DAL.AppointmentDAL;
import Enigma.ParkingProject.service.AppointmentService;


@ExtendWith(MockitoExtension.class)
public class AppointmentSystemTest {

    @Mock
    AppointmentDAL appointmentDAL;

    @InjectMocks
    AppointmentService appointmentService;

    Appointment testAppointment;
    Appointment testAppointment2;

    List<Appointment> testAppointments;


    @BeforeEach
    public void setUp()  {

        testAppointment = new Appointment(100, 1, "test1@test.com","12-09-2020", "13-09-2020");
        testAppointment2 = new Appointment(200, 2, "test2@test.com","14-04-2020", "14-04-2020");

        testAppointments = new ArrayList<Appointment>();
        testAppointments.add(testAppointment);
        testAppointments.add(testAppointment2);
    }

    @Test
    public void getAllAppointmentsTest() throws Exception
    {
        when(appointmentDAL.getAllAppointments()).thenReturn(testAppointments);
        List<Appointment>AppointmentList = appointmentService.getAllAppointments();
        Assertions.assertEquals(testAppointments, AppointmentList);
    }

    @Test
    public void getAppointmentByIdTest() 
    {
        when(appointmentDAL.getAppointmentById(any(Integer.class))).thenReturn(testAppointment2);
        Appointment mainAppointment = appointmentService.getAppointmentById(200);
        Assertions.assertEquals(testAppointment2, mainAppointment);

    }

    // @Test
    // public void ScanAppointmentTest() 
    // {
    //     when(appointmentDAL.getAllAppointments()).thenReturn(testAppointments);
    //     when(appointmentDAL.getAppointmentById(any(Integer.class))).thenReturn(testAppointment);
    //     Appointment mainAppointment = appointmentService.ScanAppointment(1);
    //     Assertions.assertEquals(testAppointment, mainAppointment);

    // }

   

}
