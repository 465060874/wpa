package com.wpa.test.student1;

import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentListViewModel implements ViewModel {
    private ObservableList<Student1> student1s = FXCollections.observableArrayList();
    @InjectScope
    private StudentScope studentScope;


    public StudentScope getStudentScope() {
        return studentScope;
    }

    public void setStudentScope(StudentScope studentScope) {
        this.studentScope = studentScope;
    }

    public void initialize() {
        initializeStudents();
    }

    private void initializeStudents() {
        student1s.addAll(
                new Student1("John Doe", 20, "Junior"),
                new Student1("Jane Doe", 22, "Senior")
        );
    }

    public ObservableList<Student1> getStudents() {
        return student1s;
    }

    public void editStudent(Student1 student1) {
        studentScope.setSelectedStudent(student1);
    }
}

