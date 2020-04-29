package be.pxl.student.dao;

import be.pxl.student.entity.Account;

import java.util.List;

public interface AccountDao {
    List<Account> findAllAccounts();
    Account findAccountById(long accountId);
    Account findAccountByName(String name);
    Account findAccountByIBAN(String IBAN);
    Account updateAccount(Account account);
    Account createAccount(Account account);

    void removeAccount(Account account);
}
