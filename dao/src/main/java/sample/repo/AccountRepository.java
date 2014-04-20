package sample.repo;

import sample.ents.Account;

import java.util.Date;
import java.util.List;

/**
 * User: jules
 * Date: 4/20/14
 */
public interface AccountRepository {

    Account findById(Integer id);

    List<Account> findByName(String name);

    List<Account> findByDob(Date dob);

    void save(Account account);
}
