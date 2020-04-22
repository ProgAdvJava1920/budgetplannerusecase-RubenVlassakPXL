package be.pxl.student.rest.resources;

public class ErrorMessage {
    private Exception exception;

    public ErrorMessage(Exception e) {
        this.exception = e;
    }
}
