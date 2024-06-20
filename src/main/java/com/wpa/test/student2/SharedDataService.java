package com.wpa.test.student2;

import jakarta.annotation.PostConstruct;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

@Service
public class SharedDataService {
    private String data;
    private Student student;
    private final ObjectProperty<Student> selectedStudent = new SimpleObjectProperty<>();
    private final ObservableList<Student> students = FXCollections.observableArrayList();

    @PostConstruct
    public void init(){
        getStudents().add(new Student("Alice", 20, "Senior"));
        getStudents().add(new Student("Bob", 21, "Junior"));
    }


    public ObservableList<Student> getStudents() {
        return students;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public ObjectProperty<Student> selectedStudentProperty() {
        return selectedStudent;
    }

    public Student getSelectedStudent() {
        return selectedStudent.get();
    }
}
