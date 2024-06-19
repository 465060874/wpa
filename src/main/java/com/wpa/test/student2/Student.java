package com.wpa.test.student2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private StringProperty name;
    private IntegerProperty age;
    private StringProperty grade;

    public Student(String name, int age, String grade) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.grade = new SimpleStringProperty(grade);
    }

    public String getName() { return name.get(); }
    public void setName(String value) { name.set(value); }
    public StringProperty nameProperty() { return name; }

    public int getAge() { return age.get(); }
    public void setAge(int value) { age.set(value); }
    public IntegerProperty ageProperty() { return age; }

    public String getGrade() { return grade.get(); }
    public void setGrade(String value) { grade.set(value); }
    public StringProperty gradeProperty() { return grade; }
}
