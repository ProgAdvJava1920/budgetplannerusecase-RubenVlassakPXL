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

    public Account map(String line) throws InvalidDateTimeException, InvalidAmountException {
        LocalDateTime dateTime;
        float amount;
        String currency;
        String detail;
        Account account;
        try {
            String[] accountProperties = line.split(",");
            account = new Account();
            account.setName(accountProperties[0]);
            account.setIBAN(accountProperties[1]);
            account.setPayments(new ArrayList<>());
            try {
                dateTime = LocalDateTime.parse(accountProperties[3], FORMATTER);
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException("Not a correct date format given");
            }
            try {
                amount = Float.parseFloat(accountProperties[4]);
            } catch (NullPointerException | NumberFormatException e) {
                throw new InvalidAmountException("Not a correct payment amount given");
            }
            currency = accountProperties[5];
            detail = accountProperties[6];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }

        Payment payment = new Payment(dateTime, amount, currency, detail);
        account.getPayments().add(payment);
        return account;
    }
}
