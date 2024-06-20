package com.wpa.test.student1;

import com.wpa.test.student2.Student;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import jakarta.inject.Inject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentEditViewModel implements ViewModel {
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty age = new SimpleIntegerProperty();
    private StringProperty grade = new SimpleStringProperty();
    @InjectScope
    private StudentScope studentScope;

    @Inject
    public void setStudentScope(StudentScope studentScope) {
        this.studentScope = studentScope;
    }

    public Student1 getStudent() {
        return studentScope.getSelectedStudent();
    }

    public void initialize() {
//        studentScope.selectedStudentProperty().addListener((obs, oldStudent1, newStudent1) -> {
//            if (newStudent1 != null) {
//                name.set(newStudent1.getName());
//                age.set(newStudent1.getAge());
//                grade.set(newStudent1.getGrade());
//            }
//        });
//        name.set(studentScope.getSelectedStudent().getName());
//        age.set(studentScope.getSelectedStudent().getAge());
//        grade.set(studentScope.getSelectedStudent().getGrade());
    }

    public void saveStudent() {
        //这个数据是已经改变了的，不需要再次获取
        Student1 student1 = studentScope.getSelectedStudent();
//        if (student1 != null) {
//            student1.setName(name.get());
//            student1.setAge(age.get());
//            student1.setGrade(grade.get());
//        }
    }

    public StringProperty nameProperty() { return name; }
    public IntegerProperty ageProperty() { return age; }
    public StringProperty gradeProperty() { return grade; }
}


