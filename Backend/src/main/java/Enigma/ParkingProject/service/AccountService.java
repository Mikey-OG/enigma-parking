package Enigma.ParkingProject.service;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private final List<Account> accountList = new ArrayList<>();

         IAccountRepository repo;

        @Autowired
        public AccountService(IAccountRepository IAccountRepository) {
            //this.IAccountRepository = IAccountRepository;
            this.repo = repo;
        }

        public List<Account> getAccountList()
        {
            return repo.findAll();
        }

    public Account getAccountById(int accountId) {

        return repo.findAll().get(accountId);

    }

    public void deleteAccount(Account account) {

        repo.delete(account);

    }

    public void addAccount(Account account) {

        repo.save(account);
    }

    public boolean updateAccount(Account account) {
        Account old = this.getAccountById(account.getAccountId());
        if (old == null) {
            return false;
        }

        repo.save(account).setFirstName(account.getFirstName());
        repo.save(account).setLastName(account.getLastName());
        repo.save(account).setLicensePlate(account.getLicensePlate());
        repo.save(account).setPhoneNumber(account.getPhoneNumber());

        return true;


    }






}
