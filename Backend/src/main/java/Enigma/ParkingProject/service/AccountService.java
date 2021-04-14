package Enigma.ParkingProject.service;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.repository.IAccountRepository;
import Enigma.ParkingProject.serviceinterfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements IAccountService {

        @Autowired
         IAccountRepository repo;


//        public AccountService(IAccountRepository IAccountRepository) {
//            //this.IAccountRepository = IAccountRepository;
//            this.repo = repo;
//        }

        @Override
        public List<Account> getAccountList()
        {
            return repo.findAll();
        }

    @Override
    public Account getAccountById(int accountId) {

        return repo.findAll().get(accountId);

    }

    @Override
    public void deleteAccount(Account account) {

        repo.delete(account);

    }

    @Override
    public void addAccount(Account account) {

        repo.save(account);
    }

    @Override
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
