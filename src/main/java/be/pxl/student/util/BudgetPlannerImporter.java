package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
    private static final Logger LOGGER = LogManager.getLogger(BudgetPlannerImporter.class);
    private PathMatcher csvMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");
    private AccountMapper accountMapper = new AccountMapper();
    private CounterAccountMapper counterAccountMapper = new CounterAccountMapper();
    private PaymentMapper paymentMapper = new PaymentMapper();
    private Map<String, Account> createdAccounts = new HashMap<>(); // store the accounts in hashmap so not too many calls to database have to be made
    private EntityManager entityManager;

    public BudgetPlannerImporter(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void importCsv(Path path) {
        // test of het bestand een CSV is
        if (!csvMatcher.matches(path)) {
            // accollades worden vervangen door de parameter
            LOGGER.error("Invalid file: .csv expected. Provided: {}", path);
            return;
        }
        if (!Files.exists(path)) {
            LOGGER.error("File {} does not exist.", path);
            return;
        }

        // properste manier om bufferedreader aan te maken
        try (BufferedReader reader = Files.newBufferedReader(path)) { // try with resources
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            String line = null;
            reader.readLine(); // skip first line
            while ((line = reader.readLine()) != null) {
                try {
//                    Account account = accountMapper.map(line);
                    Payment payment = paymentMapper.map(line);
                    payment.setAccount(getOrCreateAccount(accountMapper.map(line)));
                    payment.setCounterAccount(getOrCreateAccount(counterAccountMapper.map(line)));
                    entityManager.persist(payment);
                    LOGGER.debug(accountMapper.map(line));
                } catch (InvalidPaymentException e) {
                    LOGGER.error("Error while mapping line: {}", e.getMessage());
                }
            }
            tx.commit();
        } catch (IOException e) {
            LOGGER.fatal("An error occurred while reading file: {}", path);
        }
    }

    private Account getOrCreateAccount(Account account) {
        if (createdAccounts.containsKey(account.getIBAN())) {
            return createdAccounts.get(account.getIBAN());
        }
        entityManager.persist(account);
        createdAccounts.put(account.getIBAN(),account);
        return account;
    }


}
