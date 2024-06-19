package com.wpa.test.student1;

import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class StudentScope implements Scope {
    private ObjectProperty<Student1> selectedStudent = new SimpleObjectProperty<>();

    public Student1 getSelectedStudent() {
        return selectedStudent.get();
    }

    public void setSelectedStudent(Student1 student1) {
        this.selectedStudent.set(student1);
    }

    public ObjectProperty<Student1> selectedStudentProperty() {
        return selectedStudent;
    }
}

