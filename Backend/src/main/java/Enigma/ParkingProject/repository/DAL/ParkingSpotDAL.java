package Enigma.ParkingProject.repository.DAL;

import Enigma.ParkingProject.model.Entity.ParkingSpotEntity;
import Enigma.ParkingProject.repository.IParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingSpotDAL {
    @Autowired
    private IParkingSpotRepository repo;
    public void SaveSpace(ParkingSpotEntity spot)
    {
        repo.save(spot);
    }

    public List<ParkingSpotEntity> getAllSpots()
    {
        return repo.findAll();
    }
}
