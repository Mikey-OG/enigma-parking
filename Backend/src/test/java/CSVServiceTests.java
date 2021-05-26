import Enigma.ParkingProject.model.ParkingSpotCSV;
import Enigma.ParkingProject.service.CSVService;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
@
@SpringBootTest
public class CSVServiceTests {
    @Test
    public void CSVScanSuccessTest() throws IOException, CsvException {

        @Autowired
        CSVService service;

        String filename = "c:/ParkingSpots.csv";

        service.getCSv(filename);

        List<ParkingSpotCSV> parkingspots = service.getallSpots();

        Assertions.assertEquals(parkingspots.size(), 10);


    }
}
