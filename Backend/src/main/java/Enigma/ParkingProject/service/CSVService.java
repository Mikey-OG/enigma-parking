package Enigma.ParkingProject.service;

import Enigma.ParkingProject.model.Entity.ParkingSpotEntity;
import Enigma.ParkingProject.model.ParkingSpotCSV;
import Enigma.ParkingProject.repository.DAL.ParkingSpotDAL;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    private ParkingSpotDAL dal;
    public void getCSv(String filename) throws IOException, CsvException {
        List<ParkingSpotEntity> parkingspots = new ArrayList<>();


        List<ParkingSpotCSV> spots = new CsvToBeanBuilder(new FileReader(filename))
                .withType(ParkingSpotCSV.class)
                .build()
                .parse();
        if (spots != null)
        {
            for (ParkingSpotCSV spot: spots) {
                ParkingSpotEntity parkingSpotEntity = new ParkingSpotEntity(spot.getSpotId(), spot.getOccupied());
                dal.SaveSpace(parkingSpotEntity);
            }
        }
        else
        {
            throw new CsvException("The csv file is invalid");
        }
    }
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
}





