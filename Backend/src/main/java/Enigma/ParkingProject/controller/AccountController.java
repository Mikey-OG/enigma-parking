package Enigma.ParkingProject.controller;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.repository.DataStore;
import Enigma.ParkingProject.service.MailService;
import Enigma.ParkingProject.service.NotificationService;
import Enigma.ParkingProject.service.Sms;
import Enigma.ParkingProject.serviceinterfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService service;

    @Autowired
    private MailService email;

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
        Account account = service.getAccountById(id);

        if(account != null){
            return ResponseEntity.ok().body(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    //POST at http://localhost:XXXX/account
    public ResponseEntity<Account> createAccount(@RequestBody Account newAccount) {

            service.addAccount(newAccount);
            String url = "account" + "/" + newAccount.getLicensePlate(); // url of the created account
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        //}

    }

    @PutMapping("{account}")
    //PUT at http://localhost:XXXX/account/{accountID}
    public ResponseEntity<Account> updateAccount(@PathVariable("account") int accountID,  @RequestParam("licenseplate") String LicensePlate, @RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName, @RequestParam("phone") String phoneNo) {
        Account account = service.getAccountById(accountID);
        if (account == null){
            return new ResponseEntity("Please provide a valid license plate.",HttpStatus.NOT_FOUND);
        }

        account.setLicensePlate(LicensePlate);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setPhoneNumber(phoneNo);
        return ResponseEntity.noContent().build();
    }

    // Get all accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(@RequestParam(value = "accounts") Optional<String> licensePlate ) {
        List<Account> accounts = null;

        accounts= service.getAccountList();


        if(accounts != null) {
            return ResponseEntity.ok().body(accounts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePost(@PathVariable Account account) {
        service.deleteAccount(account);
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

    @RequestMapping("/Email")
    public void Email() {
        email.sendEmail("lucmoonen@live.nl");
    }

    @RequestMapping("/notification")
    public void Notification() throws AWTException {
        if (SystemTray.isSupported()) {
            NotificationService td = new NotificationService();
            td.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
    }

}
