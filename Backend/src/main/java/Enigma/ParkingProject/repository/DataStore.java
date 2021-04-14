package Enigma.ParkingProject.repository;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.serviceinterfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class DataStore {

    private final List<Account> accountList = new ArrayList<>();

    @Autowired
    IAccountRepository repo;


//    public DataStore(){
//        this.repo = repo;
//        accountList.add(new Account(1, "01-VBB-1", "Henk", "Broers", "+310623848125"));
//        accountList.add(new Account(2, "92-HXK-9", "Sem", "de Jong", "+310624001200"));
//        accountList.add(new Account(3, "25-BGD-2", "Daan", "Jansen", "+310622154985"));
//        accountList.add(new Account(4, "FS-VDB-2", "Luuk", "de Vries", "+310625132091"));
//        accountList.add(new Account(5, "014-DB-0", "Thijs", "van Dijk", "+310625192691"));
//        accountList.add(new Account(6, "D-12-BDB", "Lieke", "Bakker", "+310640192939"));
//        accountList.add(new Account(7, "NBK-11-S", "Stijn", "Visser", "+310645874574"));
//        accountList.add(new Account(8, "23-MMA-5", "Sanne", "Smit", "+310630570900"));
//        accountList.add(new Account(9, "223-MK-2", "Roos", "de Boer", "+310633761781"));
//        accountList.add(new Account(10, "M-99-DFV", "Evi", "Mulder", "+310687565044"));
//        accountList.add(new Account(11, "GD-VBR-4", "Teun", "de Groot", "+310682664383"));
//        accountList.add(new Account(12, "MDJ-23-1", "Siem", "Bos", "+310654501233"));
//        accountList.add(new Account(13, "90-MER-7", "Gijs", "Dekker", "+310690699314"));
//        accountList.add(new Account(14, "T-93-MAE", "Jasmijn", "de Graaf", "+310662521816"));
//        accountList.add(new Account(15, "01-MPO-2", "Willem", "Cox", "+310671189761"));
//
//    }

    public List<Account> getAccountList() {
        //return accountList;
        return repo.findAll();
    }


    public Account getAccountById(int accountId) {
        /*for (Account account : accountList) {
            if (account.getAccountId() == accountId)
                return account;
        }
        return null;*/
        return repo.findAll().get(accountId);

    }


    /*public Account getAccountByLicensePlate(String licensePlate) {
        for (Account account : accountList) {
            if (account.getLicensePlate() == licensePlate)
                return account;
        }
        return null;
       return repo.findB
    }*/

    public void deleteAccount(Account account) {
        /*Account account = getAccountById(id);
        if (account == null){
            return false;
        }
        return accountList.remove(account);*/
        repo.delete(account);

    }



    public void addAccount(Account account) {
        /*if (this.getAccountById(account.getAccountId()) != null){
            return false;
        }
        accountList.add(account);
        return true;*/
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
        /*
           old.setFirstName(account.getFirstName());
           old.setLastName(account.getLastName());
           old.setLicensePlate(account.getLicensePlate());
           old.setPhoneNumber(account.getPhoneNumber());
           */

    }

}
