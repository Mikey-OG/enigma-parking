package Enigma.ParkingProject.repository;

import Enigma.ParkingProject.model.Entity.ParkingSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParkingSpotRepository extends JpaRepository<ParkingSpotEntity, String> {
}
