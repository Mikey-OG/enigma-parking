package Enigma.ParkingProject.model;

import javax.annotation.processing.Generated;
import java.util.Objects;

public class Account {

    private int id;
    private String licensePlate;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Account(int id, String licensePlate, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Account() {
    }

    public int getAccountId() { return id; }
    public void setAccountId(int accountId) {
        this.id = id;
    }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID =" + id +
                ", License Plate ='" + licensePlate + '\'' +
                ", Name =" + firstName + " " + lastName +'\'' +
                ", Phone Number = " + phoneNumber +
                '}';
    }
}
