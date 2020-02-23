package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountMapperTest {
    private String validLine = "Jos,BE69771770897312,BE20957870192904,Sat Feb 15 05:15:31 CET 2020,3317.29,EUR,Quibusdam molestias voluptates ab magnam dolorem.";
    private AccountMapper accountMapper = new AccountMapper();

    @Test
    public void aValidLineIsMappedToAnAccount() {
        // ARRANGE
        // ACT
        // ASSERT

        Account result = accountMapper.map(validLine);

        assertNotNull(result);
        assertEquals("Jos", result.getName());
        assertEquals("BE69771770897312", result.getIBAN());

        // Test of er 1 payment in payments? je zou nog kunnen checken of payments niet null is
        assertEquals(1, result.getPayments().size());

        Payment resultPayment = result.getPayments().get(0);
        assertEquals(LocalDateTime.of(2020, 2, 15, 5, 15, 31), resultPayment.getDate());

        // Test de currency
        assertEquals("EUR", resultPayment.getCurrency());

        assertEquals(265.8, resultPayment.getAmount());
        assertEquals("Officia harum porro omnis tenetur ut.", resultPayment.getDetail());
    }
}
