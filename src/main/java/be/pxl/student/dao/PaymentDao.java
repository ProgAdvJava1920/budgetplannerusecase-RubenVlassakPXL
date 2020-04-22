package be.pxl.student.dao;

public interface PaymentDao {
    Long countPaymentsByLabel(long labelId);
}
