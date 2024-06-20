package com.wpa.test.student2;

import com.wpa.test.SharedDataService;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentListViewModel implements ViewModel {
    @InjectScope
    private StudentScope studentScope;

    @Autowired
    private SharedDataService sharedDataService;

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
