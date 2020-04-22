package be.pxl.student.dao;

import be.pxl.student.entity.Label;

public interface LabelDao {
    Label findLabelById(long id);
    Label findLabelByName(String name);
    void saveLabel(Label label);
    void removeLabel(Label labelById);
}
