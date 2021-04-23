package Enigma.ParkingProject.service;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.repository.IAccountRepository;
import Enigma.ParkingProject.serviceinterfaces.IAccountService;
import dalinterfaces.IAccountDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements IAccountService {


        IAccountDAL dal;

        @Autowired
       public AccountService(IAccountDAL dal) {
         //this.IAccountRepository = IAccountRepository;
            this.dal = dal;
       }

        @Override
        public List<Account> getAccountList()
        {
            return dal.getAccountList();
        }

    @Override
    public Account getAccountById(int accountId) {

        return dal.getAccountById(accountId);

    }

    @Override
    public void deleteAccount(Account account) {

        dal.deleteAccount(account);

    }

    @Override
    public void addAccount(Account account) {

        dal.addAccount(account);
    }

    @Override
    public boolean updateAccount(Account account) {
        Account old = this.getAccountById(account.getAccountId());
        if (old == null) {
            return false;
        }

        /*repo.save(account).setFirstName(account.getFirstName());
        repo.save(account).setLastName(account.getLastName());
        repo.save(account).setLicensePlate(account.getLicensePlate());
        repo.save(account).setPhoneNumber(account.getPhoneNumber());*/
        dal.updateAccount(account);

        return true;


    }






}
