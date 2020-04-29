package be.pxl.student.util.exception;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String name) {
        super("The account with name [" + name + "] was not found.");
    }

    public AccountNotFoundException() {
        super("The account was not found.");
    }
}
