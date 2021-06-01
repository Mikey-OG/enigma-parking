package Enigma.ParkingProject.model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "parkingspot")
public class ParkingSpotEntity {
    @Id
    @Column(name = "spotId")
    private String spotID;
    @Column(name = "occupied")
    private String occupied;
    @Column(name = "guestId")
    private int guestId;
    public ParkingSpotEntity() {

    }


    public String getSpotID() {
        return spotID;
    }

    public void setSpotID(String spotID) {
        this.spotID = spotID;
    }

    public String getOccupied() {
        return occupied;
    }

    public void setOccupied(String occupied) {
        this.occupied = occupied;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public ParkingSpotEntity(String spotID, String occupied, int guestId) {
        this.spotID = spotID;
        this.occupied = occupied;
        this.guestId = guestId;
    }
}
