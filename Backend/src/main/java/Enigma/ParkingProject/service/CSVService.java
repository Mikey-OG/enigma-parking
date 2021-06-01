package Enigma.ParkingProject.service;

import Enigma.ParkingProject.model.Entity.ParkingSpotEntity;
import Enigma.ParkingProject.model.ParkingSpotCSV;
import Enigma.ParkingProject.repository.DAL.ParkingSpotDAL;
import Enigma.ParkingProject.serviceinterfaces.ICSVService;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import org.aspectj.weaver.IClassFileProvider;
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
    public void getCSv(String filename) throws IOException, CsvException {
        List<ParkingSpotEntity> parkingspots = new ArrayList<>();


        try {
            List<ParkingSpotCSV> spots = new CsvToBeanBuilder(new FileReader(filename))
                    .withType(ParkingSpotCSV.class)
                    .build()
                    .parse();
            if (spots != null) {
                for (ParkingSpotCSV spot : spots) {
                    ParkingSpotEntity parkingSpotEntity = new ParkingSpotEntity(spot.getSpotId(), spot.getOccupied(), spot.getGuestId());
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
    public List<ParkingSpotCSV> getallSpots()
    {
        List<ParkingSpotEntity> list = dal.getAllSpots();
        List<ParkingSpotCSV>  returnlist = new ArrayList<>();
        for (ParkingSpotEntity p: list) {
            ParkingSpotCSV spot = new ParkingSpotCSV(p.getSpotID(),p.getOccupied(), p.getGuestId());
            returnlist.add(spot);
        }
        return returnlist;

    }
    public List<ParkingSpotCSV> getAllAvailableSpots()
    {
        List<ParkingSpotEntity> spots = dal.getAllAvailableSpots();
        List<ParkingSpotCSV> returnlist = new ArrayList<>();
        for (ParkingSpotEntity p: spots) {
            ParkingSpotCSV spotCSV = new ParkingSpotCSV(p.getSpotID(),p.getOccupied(),p.getGuestId());
            returnlist.add(spotCSV);

        }
        return returnlist;
    }
    public boolean assignSpot(int guestId)
    {
        List<ParkingSpotEntity> spots = dal.getAllAvailableSpots();
        if (spots == null)
        {
            return false;
        }
        else
        {
            ParkingSpotEntity first = spots.get(0);
            ParkingSpotEntity assigned = new ParkingSpotEntity(first.getSpotID(), "yes", guestId);
            dal.assignSpot(assigned);
            return true;
        }
    }
    public void unassignSpot(int guestId)
    {
        ParkingSpotEntity used = dal.getSpotbyId(guestId);
        ParkingSpotEntity unassign = new ParkingSpotEntity(used.getSpotID(),"no", 0);
        dal.assignSpot(unassign);
    }

}





