package Enigma.ParkingProject.service;

import Enigma.ParkingProject.model.Entity.ParkingSpotEntity;
import Enigma.ParkingProject.model.ParkingSpotCSV;
import Enigma.ParkingProject.repository.DAL.ParkingSpotDAL;
import Enigma.ParkingProject.serviceinterfaces.ICSVService;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVService implements ICSVService {
    @Autowired
    private ParkingSpotDAL dal;
    @Override
    public void getCSv(String filename) throws IOException, CsvException {
        List<ParkingSpotEntity> parkingspots = new ArrayList<>();


        try {
            List<ParkingSpotCSV> spots = new CsvToBeanBuilder(new FileReader(filename))
                    .withType(ParkingSpotCSV.class)
                    .build()
                    .parse();

            if (spots != null) {
                for (ParkingSpotCSV spot : spots) {
                    ParkingSpotEntity parkingSpotEntity = new ParkingSpotEntity(spot.getSpotId(), spot.getOccupied());
                    dal.assignSpot(parkingSpotEntity);
                }
            } else {
                throw new CsvException("The csv file is invalid");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
    @Override
    public List<ParkingSpotCSV> getallSpots()
    {
        List<ParkingSpotEntity> list = dal.getAllSpots();
        List<ParkingSpotCSV>  returnlist = new ArrayList<>();
        for (ParkingSpotEntity p: list) {
            ParkingSpotCSV spot = new ParkingSpotCSV(p.getSpotID(),p.getOccupied());
            returnlist.add(spot);
        }
        return returnlist;

    }
    @Override
    public boolean checkForAvailableSpots()
    {
        List<ParkingSpotEntity> spots = dal.getAllAvailableSpots();
        List<ParkingSpotCSV> returnlist = new ArrayList<>();
        for (ParkingSpotEntity p: spots) {
            ParkingSpotCSV spotCSV = new ParkingSpotCSV(p.getSpotID(),p.getOccupied());
            returnlist.add(spotCSV);

        }
        if(returnlist != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}





