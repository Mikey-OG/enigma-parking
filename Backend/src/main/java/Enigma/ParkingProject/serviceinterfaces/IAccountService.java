package Enigma.ParkingProject.serviceinterfaces;

import Enigma.ParkingProject.model.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getAccountList();

    Account getAccountById(int accountId);

    Account getAccountByLicensePlate(String licensePlate);

    boolean deleteAccount(int id);

    boolean addAccount(Account account);

    boolean updateAccount(Account account);
}
