package be.pxl.student.util;

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

    public void importCsv(Path path) {
        // test of het bestand een CSV is
        if (!csvMatcher.matches(path)) {
            // accolades worden vervangen door de parameter
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
                System.out.println(line);
            }
        } catch (IOException e) {
            LOGGER.fatal("An error occurred while reading file: {}", path);
        }
    }
}
