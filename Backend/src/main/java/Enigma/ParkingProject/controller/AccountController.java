package Enigma.ParkingProject.controller;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.repository.DataStore;
import Enigma.ParkingProject.service.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    public AccountController() {
    }

    private static final DataStore dataStore = new DataStore();

//    @GetMapping("{account}") //GET at http://localhost:XXXX/account/MS7878DSB
//    public ResponseEntity<Account> getAccountPath(@PathVariable(value = "account") String licensePlate) {
//        Account account = dataStore.getAccountByLicensePlate(licensePlate);
//
//        if(account != null) {
//            return ResponseEntity.ok().body(account);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("{id}")
    public ResponseEntity<Account> getAccountPath(@PathVariable(value = "id") int id){
        Account account = dataStore.getAccountById(id);

        if(account != null){
            return ResponseEntity.ok().body(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    //POST at http://localhost:XXXX/account
    public ResponseEntity<Account> createAccount(@RequestBody Account newAccount) {
        if (!dataStore.addAccount(newAccount)){
            String entity =  "Account with license plate " + newAccount.getLicensePlate() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "account" + "/" + newAccount.getLicensePlate(); // url of the created account
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }

    }

    @PutMapping("{account}")
    //PUT at http://localhost:XXXX/account/{licensePlate}
    public ResponseEntity<Account> updateAccount(@PathVariable("account") String licensePlate,  @RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName, @RequestParam("phone") String phoneNo, @RequestParam("licenseplate") String newLicensePlate) {
        Account account = dataStore.getAccountByLicensePlate(licensePlate);
        if (account == null){
            return new ResponseEntity("Please provide a valid license plate.",HttpStatus.NOT_FOUND);
        }


        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setPhoneNumber(phoneNo);
        account.setLicensePlate(newLicensePlate);
        return ResponseEntity.noContent().build();
    }

    // Get all accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(@RequestParam(value = "accounts") Optional<String> licensePlate ) {
        List<Account> accounts = null;

        accounts= dataStore.getAccountList();


        if(accounts != null) {
            return ResponseEntity.ok().body(accounts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePost(@PathVariable int id) {
        dataStore.deleteAccount(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();

    }

    @RequestMapping("/smsAvailable")
    public void smsAvailable() {
        Sms sms = new Sms();
        sms.SendSmsParkingAvailable();
    }

    @RequestMapping("/smsFull")
    public void smsFull() {
        Sms sms = new Sms();
        sms.SendSmsParkingFull();
    }




}
