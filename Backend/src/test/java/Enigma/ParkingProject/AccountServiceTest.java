package Enigma.ParkingProject;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.repository.IAccountRepository;
import Enigma.ParkingProject.service.AccountService;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    IAccountRepository AccountRepository;

    Account testAccount;
    Account testAccount2;

    List<Account> testAccounts;

    @InjectMocks
    AccountService accountService;

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
        when(AccountRepository.findAll()).thenReturn(testAccounts);
        List<Account> AccountList = accountService.getAccountList();
        Assertions.assertEquals(testAccounts, AccountList);
    }
    
}
