package be.pxl.student;

import be.pxl.student.entity.Account;
import be.pxl.student.util.BudgetPlannerImporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BudgetPlanner {
    private static final Logger LOGGER = LogManager.getLogger(BudgetPlanner.class);

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("musicdb");
            entityManager = entityManagerFactory.createEntityManager();
            BudgetPlannerImporter budgetPlannerImporter = new BudgetPlannerImporter(entityManager);
            LOGGER.info("start reading file");
            budgetPlannerImporter.importCsv(Paths.get("src/main/resources/account_payments.csv"));
            LOGGER.info("finished reading file");
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}
