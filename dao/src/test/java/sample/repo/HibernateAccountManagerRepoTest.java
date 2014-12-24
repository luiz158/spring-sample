package sample.repo;

import org.hibernate.PropertyValueException;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sample.ents.Account;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: jules
 * Date: 4/20/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/app-dao-config.xml", "classpath:spring/test-db-config.xml"})
@Transactional
public class HibernateAccountManagerRepoTest {

    @Autowired private AccountRepository accountRepo;

    @Test
    public void shouldHaveRepo() {
        assertNotNull(accountRepo);
    }

    /* we introduce a few objects so we can test them*/
    @Before
    public void fill() {
        LocalDate d = new LocalDate();

        Account account = new Account();
        account.setName("test1");
        account.setEmail("test1@test.tst");
        account.setDateOfBirth(d.toDate());
        accountRepo.save(account);

        account = new Account();
        account.setName("test2");
        account.setEmail("test2@test.tst");
        account.setDateOfBirth(d.toDate());
        accountRepo.save(account);
    }

    @Test
    public void findByName() {
        List<Account> accounts = accountRepo.findByName("test");
        assertNotNull(accounts);
        assertEquals(2, accounts.size());
    }

    @Test
    public void findByDob() {
        LocalDate d = new LocalDate();
        List<Account> accounts = accountRepo.findByDob(d.toDate());
        assertNotNull(accounts);
        assertEquals(2, accounts.size());
    }

    @Test
    public void update() {
        Account account = new Account();
        account.setName("test1");
        account.setEmail("test1@test.tst");
        account.setDateOfBirth(new DateTime().toDate());
        accountRepo.save(account);

        account = accountRepo.findById(account.getId());
        assertNotNull(account);
        account.setEmail("new@email.com");
        accountRepo.save(account);

        account = accountRepo.findById(account.getId());
        assertEquals("new@email.com", account.getEmail());
    }

    @Test (expected = PropertyValueException.class)
    public void createNoDob() {
        Account account = new Account();
        account.setName("test1");
        account.setEmail("test1@test.tst");
        accountRepo.save(account);
    }
}
