package be.pxl.student.dao.impl;

import be.pxl.student.dao.LabelDao;
import be.pxl.student.entity.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class LabelDaoImpl implements LabelDao {
    private static final Logger LOGGER = LogManager.getLogger(LabelDaoImpl.class);
    private EntityManager entityManager;

    public LabelDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Label> findAllLabels() {
        TypedQuery<Label> findAllLabels = entityManager.createNamedQuery("findAllLabels", Label.class);
        LOGGER.info("query for all labels");
        try {
            return findAllLabels.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Label findLabelById(long id) {
        TypedQuery<Label> findById = entityManager.createNamedQuery("findLabelById", Label.class);
        LOGGER.info(String.format("query with id[%d]", id));
        findById.setParameter("id", id);
        try {
            return findById.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Label findLabelByName(String name) {
        TypedQuery<Label> findByName = entityManager.createNamedQuery("findLabelByName", Label.class);
        LOGGER.info(String.format("query with name[%s]", name));
        findByName.setParameter("name", name);
        try {
            return findByName.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Label saveLabel(Label label) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(label);
        transaction.commit();
        return label;
    }

    @Override
    public void removeLabel(Label label) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(label);
        transaction.commit();
    }
}
