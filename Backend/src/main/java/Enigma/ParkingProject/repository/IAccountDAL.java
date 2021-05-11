package Enigma.ParkingProject.repository;

import Enigma.ParkingProject.model.Account;

import java.util.List;

public interface IAccountDAL {
    List<Account> getAccountList();

    Account getAccountById(int accountId);

    void deleteAccount(int account);

    void addAccount(Account account);

    boolean updateAccount(Account account);
}
