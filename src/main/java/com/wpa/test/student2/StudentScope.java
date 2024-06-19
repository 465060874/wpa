package com.wpa.test.student2;

import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentScope implements Scope {
    private final ObservableList<Student> students = FXCollections.observableArrayList();
    private final ObjectProperty<Student> selectedStudent = new SimpleObjectProperty<>();

    public ObservableList<Student> getStudents() {
        return students;
    }

    public Student getSelectedStudent() {
        return selectedStudent.get();
    }

    public ObjectProperty<Student> selectedStudentProperty() {
        return selectedStudent;
    }
}
