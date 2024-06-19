package com.wpa.test.student2;

import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.collections.ObservableList;

public class StudentListViewModel implements ViewModel {
    @InjectScope
    private StudentScope studentScope;

    public StudentScope getStudentScope() {
        return studentScope;
    }

    public ObservableList<Student> getStudents() {
        return studentScope.getStudents();
    }

    public void editStudent(Student student) {
        studentScope.selectedStudentProperty().set(student);
    }
}
