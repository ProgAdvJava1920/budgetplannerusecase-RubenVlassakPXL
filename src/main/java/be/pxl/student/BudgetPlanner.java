package be.pxl.student;

import be.pxl.student.entity.Account;
import be.pxl.student.util.BudgetPlannerImporter;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BudgetPlanner {
    public static void main(String[] args) {
        new BudgetPlannerImporter().importCsv(Paths.get("src/main/resources/account_payments.csv"));
    }
}
