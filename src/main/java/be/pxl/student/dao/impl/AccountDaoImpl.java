package be.pxl.student.dao.impl;

import be.pxl.student.entity.Account;
import be.pxl.student.dao.AccountDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    private static final Logger LOGGER = LogManager.getLogger(AccountDaoImpl.class);
    private EntityManager entityManager;

    public AccountDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Account> findAllAccounts() {
        TypedQuery<Account> findAllAccounts = entityManager.createNamedQuery("findAllAccounts", Account.class);
        LOGGER.info("query for all accounts");
        try {
            return findAllAccounts.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Account findAccountById(long accountId) {
        TypedQuery<Account> findById = entityManager.createNamedQuery("findById", Account.class);
        LOGGER.info(String.format("query with id[%s]", accountId));
        findById.setParameter("id", accountId);
        try {
            return findById.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Account findAccountByName(String name) {
        TypedQuery<Account> findByName = entityManager.createNamedQuery("findByName", Account.class);
        LOGGER.info(String.format("query with name[%s]", name));
        findByName.setParameter("name", name);
        try {
            return findByName.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Account findAccountByIBAN(String iban) {
        TypedQuery<Account> findByIBAN = entityManager.createNamedQuery("findByIBAN", Account.class);
        LOGGER.info(String.format("query with IBAN [%s]", iban));
        findByIBAN.setParameter("iban", iban);
        try {
            return findByIBAN.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Account updateAccount(Account account) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        account = entityManager.merge(account);
        transaction.commit();
        return account;
    }

    @Override
    public Account createAccount(Account account) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(account);
        transaction.commit();
        return account;
    }

    @Override
    public void removeAccount(Account account) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(account);
        transaction.commit();
    }
}
