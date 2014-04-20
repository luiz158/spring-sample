package sample.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import sample.ents.Account;
import sample.util.AccountSearchCriteria;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static sample.ents.Account.*;

/**
 * User: jules
 * Date: 4/20/14
 */
@Repository("accountRepo")
@Transactional
public class HibernateAccountRepo implements AccountRepository {

    private SessionFactory sessionFactory;

    /**
     * Creates a new Hibernate account manager.
     *
     * @param sessionFactory the Hibernate session factory
     */
    @Autowired
    public HibernateAccountRepo(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Account findById(Integer id) {
        return (Account) getCurrentSession().getNamedQuery(FIND_BY_ID).setParameter("id", id).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Account> findByName(String name) {
        return (List<Account>) getCurrentSession().getNamedQuery(FIND_BY_NAME).setParameter("name", "%" + name + "%").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Account> findByName(AccountSearchCriteria searchCriteria) {
        return (List<Account>) getCurrentSession().getNamedQuery(FIND_BY_NAME)
                .setParameter("name", "%" + searchCriteria.getSearchString() + "%").setFirstResult(
                        searchCriteria.getPage()
                                * searchCriteria.getMaximumResults())
                .setMaxResults(searchCriteria.getMaximumResults()).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Account> findByDob(Date dob) {
        return (List<Account>) getCurrentSession().getNamedQuery(FIND_BY_DOB).setParameter("dob", dob).list();
    }

    @Override
    public void save(Account account) {
        getCurrentSession().saveOrUpdate(account);
    }

    /**
     * Returns the session associated with the ongoing transaction.
     *
     * @return the current session
     */
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
