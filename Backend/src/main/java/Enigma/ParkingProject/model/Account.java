package Enigma.ParkingProject.model;

import javax.annotation.processing.Generated;
import java.util.Objects;

public class Account {

    private int accountId;
    private String licensePlate;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Account(int accountId, String licensePlate, String firstName, String lastName, String phoneNumber) {
        this.accountId = accountId;
        this.licensePlate = licensePlate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Account() {
    }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
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
        return accountId == account.accountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID =" + accountId +
                ", License Plate ='" + licensePlate + '\'' +
                ", Name =" + firstName + " " + lastName +'\'' +
                ", Phone Number = " + phoneNumber +
                '}';
    }
}
