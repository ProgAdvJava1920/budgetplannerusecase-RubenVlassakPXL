package be.pxl.student.util;

import be.pxl.student.entity.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
    private static final Logger LOGGER = LogManager.getLogger(BudgetPlannerImporter.class);
    private PathMatcher csvMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");
    private AccountMapper accountMapper = new AccountMapper();

    public void importCsv(Path path) {
        // test of het bestand een CSV is
        if (!csvMatcher.matches(path)) {
            // accollades worden vervangen door de parameter
            LOGGER.error("Invalid file: .csv expected. Provided: {}", path);
            return;
        }
        if (!Files.exists(path)) {
            LOGGER.error("File {} does not exist.", path);
        }
        // properste manier om bufferedreader aan te maken
        try (BufferedReader reader = Files.newBufferedReader(path)) { // try with resources
            String line = null;
            while ((line = reader.readLine()) != null) {
                try {
                    Account account = accountMapper.map(line);
                    LOGGER.info(account.toString());
                } catch (InvalidDateTimeException e) {
                    LOGGER.error("Invalid payment date");
                } catch (InvalidAmountException e) {
                    LOGGER.error("Invalid payment amount");
                } catch (NullPointerException e) {
                    LOGGER.error("Incorrect format");
                }
            }
        } catch (IOException e) {
            LOGGER.fatal("An error occurred while reading file: {}", path);
        }
    }
}
