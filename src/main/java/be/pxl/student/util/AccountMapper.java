package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class AccountMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

    public Account map(String validLine) {
        String[] accountProperties = validLine.split(",");
        Account account = new Account();
        account.setName(accountProperties[0]);
        account.setIBAN(accountProperties[1]);
        account.setPayments(new ArrayList<>());
        Payment payment = new Payment(LocalDateTime.parse(accountProperties[3], FORMATTER), Float.parseFloat(accountProperties[4]), accountProperties[5], accountProperties[6]);
        account.getPayments().add(payment);
        return account;
    }
}
