package dao;

import be.pxl.student.entity.Account;

public interface AccountDao {
    Account findAccountByName(String name);
    Account findAccountByIBAN(String IBAN);
    Account updateAccount(Account account);
    Account createAccount(Account account);
}
