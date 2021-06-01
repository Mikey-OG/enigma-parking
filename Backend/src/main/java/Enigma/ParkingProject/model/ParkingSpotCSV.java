package Enigma.ParkingProject.model;

import com.opencsv.bean.CsvBindByPosition;

import javax.persistence.*;

public class ParkingSpotCSV {
    @CsvBindByPosition(position = 0)
    private String spotId;

    @CsvBindByPosition(position = 1)
    private String occupied;

    private int guestId;

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
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

    public ParkingSpotCSV(String spotId, String occupied, int guestId) {
        this.spotId = spotId;
        this.occupied = occupied;
        this.guestId = guestId;
    }

    @Override
    public String toString() {
        return "ParkingSpotCSV{" +
                "spotId='" + spotId + '\'' +
                ", occupied='" + occupied + '\'' +
                ", guestId=" + guestId +
                '}';
    }
}
