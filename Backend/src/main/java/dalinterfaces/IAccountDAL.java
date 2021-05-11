package dalinterfaces;

import Enigma.ParkingProject.model.Account;

import java.util.List;

public interface IAccountDAL {
    List<Account> getAccountList();

    Account getAccountById(int accountId);

    void deleteAccount(Account account);

    void addAccount(Account account);

    boolean updateAccount(Account account);
}
