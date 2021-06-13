package Enigma.ParkingProject;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.repository.IAccountRepository;
import Enigma.ParkingProject.repository.DAL.AccountDAL;
import Enigma.ParkingProject.service.AccountService;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    AccountDAL accountDAL;

    @InjectMocks
    AccountService accountService;

    Account testAccount;
    Account testAccount2;

    List<Account> testAccounts;


    @BeforeEach
    public void setUp()  {

        testAccount = new Account(100, "GTRSWJEW", "john", "doe", "0617437757");
        testAccount2 = new Account(200, "GTRSWJTT", "sarah", "foo", "0617437758");

        testAccounts = new ArrayList<Account>();
        testAccounts.add(testAccount);
        testAccounts.add(testAccount2);
    }

    @Test
    public void getAllProductsTest() throws Exception
    {
        when(accountDAL.getAccountList()).thenReturn(testAccounts);
        List<Account> AccountList = accountService.getAccountList();
        Assertions.assertEquals(testAccounts, AccountList);
    }

    @Test
    public void getAccountByIdTest() 
    {
        when(accountDAL.getAccountById(any(Integer.class))).thenReturn(testAccount2);
        Account mainAccount = accountService.getAccountById(200);
        Assertions.assertEquals(testAccount2, mainAccount);

    }

    @Test
    public void updateAccountTest()
    {
        when(accountDAL.getAccountById(any(Integer.class))).thenReturn(testAccount2);
        when(accountDAL.updateAccount(any(Account.class))).thenReturn(true);
        boolean mainAccount = accountService.updateAccount(testAccount2);
        Assertions.assertEquals(true, mainAccount);
    }
    
}
