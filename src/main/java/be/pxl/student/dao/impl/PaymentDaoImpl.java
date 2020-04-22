package be.pxl.student.dao.impl;

import be.pxl.student.dao.PaymentDao;
import be.pxl.student.entity.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class PaymentDaoImpl implements PaymentDao {
    private static final Logger LOGGER = LogManager.getLogger(PaymentDaoImpl.class);
    private EntityManager entityManager;

    public PaymentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long countPaymentsByLabel(long labelId) {
        TypedQuery<Payment> findByLabel = entityManager.createNamedQuery("findByLabel", Payment.class);
        LOGGER.info(String.format("query with labelid[%d]", labelId));
        findByLabel.setParameter("labelId", labelId);
        try {
            return findByLabel.getSingleResult().getLabel().getId();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
