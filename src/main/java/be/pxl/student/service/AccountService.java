package be.pxl.student.service;

import be.pxl.student.dao.AccountDao;
import be.pxl.student.dao.impl.AccountDaoImpl;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.util.EntityManagerUtil;
import be.pxl.student.util.exception.AccountNotFoundException;

import javax.ejb.Stateless;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class AccountService {
    private AccountDao accountDao;

    public AccountService() {
        this.accountDao = new AccountDaoImpl(EntityManagerUtil.createEntityManager());
    }

    public void addPayment(String name, String counterAccountIBAN, float amount, String detail, LocalDate date) throws AccountNotFoundException {
        Account account = accountDao.findAccountByName(name);
        if (account == null) {
            throw new AccountNotFoundException(name);
        }
        Account counterAccount = accountDao.findAccountByIBAN(counterAccountIBAN);
        if (counterAccount == null) {
            counterAccount = new Account();
            counterAccount.setIBAN(counterAccountIBAN);
            counterAccount = accountDao.createAccount(counterAccount);
        }
        Payment payment = new Payment();
        payment.setCounterAccount(counterAccount);
        payment.setAmount(amount);
        payment.setDate(date.atStartOfDay());
        payment.setDetail(detail);
        account.addPayment(payment);
        accountDao.updateAccount(account);
    }

    public List<Account> findAllAccounts() {
        return accountDao.findAllAccounts();
    }

    public void removeAccount(long accountId) throws AccountNotFoundException {
        Account account = accountDao.findAccountById(accountId);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        accountDao.removeAccount(account);
    }
}
