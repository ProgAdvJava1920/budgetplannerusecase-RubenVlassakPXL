package be.pxl.student.service;

import be.pxl.student.entity.Label;
import be.pxl.student.util.exception.LabelInUseException;
import be.pxl.student.util.exception.LabelNotFoundException;
import be.pxl.student.dao.LabelDao;
import be.pxl.student.dao.PaymentDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LabelServiceRemoveLabelTest {
    private static final long LABEL_ID = 12L;

    @Mock
    private LabelDao labelDao;
    @Mock
    private PaymentDao paymentDao;
    @InjectMocks // de mocks ga je gebruiken in de labelService
    private LabelService labelService;
    private Label label;

    @BeforeEach
    public void init() {
        // maak al mijn mock objecten klaar en plaats die op de juiste plaats, hang alles correct aan elkaar
        MockitoAnnotations.initMocks(this);
        // label om in de testen te gebruiken
        label = new Label();
        label.setId(LABEL_ID);
        label.setName("Clothes");
    }

    @Test
    public void anExceptionIsThrownWhenLabelIsUsed() throws LabelNotFoundException, LabelInUseException {
        when(labelDao.findLabelById(LABEL_ID)).thenReturn(label);
        when(paymentDao.countPaymentsByLabel(LABEL_ID)).thenReturn(5L);

        assertThrows(LabelInUseException.class, () -> labelService.removeLabel(LABEL_ID));

        verify(labelDao, never()).removeLabel(label);
    }

    @Test
    public void unusedLabelIsRemoved() throws LabelNotFoundException, LabelInUseException {
        when(labelDao.findLabelById(LABEL_ID)).thenReturn(label);
        when(paymentDao.countPaymentsByLabel(LABEL_ID)).thenReturn(0L);

        labelService.removeLabel(LABEL_ID);

        verify(labelDao).removeLabel(label);
    }
}
