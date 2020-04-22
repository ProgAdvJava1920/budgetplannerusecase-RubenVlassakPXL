package be.pxl.student.service;

import be.pxl.student.entity.Label;
import be.pxl.student.util.exception.DuplicateLabelException;
import be.pxl.student.util.exception.LabelInUseException;
import be.pxl.student.util.exception.LabelNotFoundException;
import be.pxl.student.dao.LabelDao;
import be.pxl.student.dao.PaymentDao;

import java.util.List;

public class LabelService {
    private LabelDao labelDao;
    private PaymentDao paymentDao;

    public LabelService(LabelDao labelDao, PaymentDao paymentDao) {
        this.labelDao = labelDao;
        this.paymentDao = paymentDao;
    }

    public void addLabel(String name) throws DuplicateLabelException {
        Label existingLabel = labelDao.findLabelByName(name);
        if (existingLabel != null) {
            throw new DuplicateLabelException("There already exists a label with name [" + name + "]");
        }
        labelDao.saveLabel(new Label(name));
    }

    public void removeLabel(long labelId) throws LabelNotFoundException, LabelInUseException {
        Label labelById = labelDao.findLabelById(labelId);
        if (labelById == null) {
            throw new LabelNotFoundException("Label with id [" + labelId + "] cannot be found.");
        }
        Long numberOfPayments = paymentDao.countPaymentsByLabel(labelId);
        if (numberOfPayments > 0) {
            throw new LabelInUseException("Label [" + labelById.getName() + "] is in use. Remove the payments first or change their label.");
        }
        labelDao.removeLabel(labelById);
    }

    public List<Label> findAllLabels() {
        return null;
    }
}
