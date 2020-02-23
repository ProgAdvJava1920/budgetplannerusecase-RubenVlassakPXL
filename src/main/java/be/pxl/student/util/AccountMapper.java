package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

public class AccountMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

    public Account map(String line) {
        String[] accountProperties = line.split(",");
        Account account = new Account();
        account.setName(accountProperties[0]);
        account.setIBAN(accountProperties[1]);
        account.setPayments(new ArrayList<>());
        LocalDateTime dateTime;
        float amount;
        String currency;
        String detail;
        try {
            dateTime = LocalDateTime.parse(accountProperties[3], FORMATTER);
            amount = Float.parseFloat(accountProperties[4]);
            currency = accountProperties[5];
            detail = accountProperties[6];
        } catch (Exception e) {
            return null;
        }
        Payment payment = new Payment(dateTime, amount, currency, detail);
        account.getPayments().add(payment);
        return account;
    }
}
