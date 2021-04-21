package Enigma.ParkingProject.repository.DAL;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.repository.IAccountDAL;
import Enigma.ParkingProject.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAL implements IAccountDAL {

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

    @Override
    public List<Account> getAccountList() {
        //return accountList;
        return repo.findAll();
    }

    @Override
    public Account getAccountById(int accountId) {
        return repo.getAccountByAccountId(accountId);
    }

    @Override
    public void deleteAccount(int accountid) {

        repo.deleteById(accountid);

    }


    @Override
    public void addAccount(Account account) {
        repo.save(account);
    }

    @Override
    public boolean updateAccount(Account account) {
        repo.save(account);
        return true;

    }

}
