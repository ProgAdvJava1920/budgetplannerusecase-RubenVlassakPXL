package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.util.exception.InvalidAmountException;
import be.pxl.student.util.exception.InvalidDateTimeException;
import be.pxl.student.util.exception.InvalidPaymentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AccountMapperTest {
    private String validLine = "Jos,BE69771770897312,BE20957870192904,Sat Feb 15 05:15:31 CET 2020,3317.29,EUR,Quibusdam molestias voluptates ab magnam dolorem.";
    private String invalidDateLine = "Jos,BE69771770897312,BE20957870192904,Feb 15 05:15:31 CET 2020,3317.29,EUR,Quibusdam molestias voluptates ab magnam dolorem.";
    private String invalidAmountLine = "Jos,BE69771770897312,BE20957870192904,Sat Feb 15 05:15:31 CET 2020,abc,EUR,Quibusdam molestias voluptates ab magnam dolorem.";
    private String invalidLine = "Jos,";
    private AccountMapper accountMapper = new AccountMapper();

//    @Test
//    public void aValidLineIsMappedToAnAccount() throws InvalidPaymentException {
//        // ARRANGE
//        // ACT
//        // ASSERT
//
//        Account result = accountMapper.map(validLine);
//        assertNotNull(result);
//        assertEquals("Jos", result.getName());
//        assertEquals("BE69771770897312", result.getIBAN());
//
//        // Test of er 1 payment in payments? je zou nog kunnen checken of payments niet null is
//        assertEquals(1, result.getPayments().size());
//
//        Payment resultPayment = result.getPayments().get(0);
//        assertEquals(LocalDateTime.of(2020, 2, 15, 5, 15, 31), resultPayment.getDate());
//
//        // Test de currency
//        assertEquals("EUR", resultPayment.getCurrency());
//
//        assertEquals(3317.29F, resultPayment.getAmount());
//        assertEquals("Quibusdam molestias voluptates ab magnam dolorem.", resultPayment.getDetail());
//    }

//    @Test
//    public void anInvalidLineReturnsANullAccount() throws InvalidPaymentException {
//        Account result = accountMapper.map(invalidLine);
//        assertNull(result);
//    }
//
//    @Test
//    public void anInvalidDateThrowsAnException() {
//        Assertions.assertThrows(InvalidDateTimeException.class,() -> accountMapper.map(invalidDateLine));
//    }
//
//    @Test
//    public void anInvalidAmountThrowsAnException() {
//        Assertions.assertThrows(InvalidAmountException.class,() -> accountMapper.map(invalidAmountLine));
//    }
}
