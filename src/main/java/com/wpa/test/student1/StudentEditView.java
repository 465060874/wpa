package com.wpa.test.student1;

import com.wpa.test.student2.Student;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class StudentEditView implements FxmlView<StudentEditViewModel> {
    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField gradeField;

    @FXML
    private Button saveButton;

    @InjectViewModel
    private StudentEditViewModel viewModel;

    public void initialize() {
        Student1 student = viewModel.getStudent();
        if (student != null) {
            nameField.textProperty().bindBidirectional(student.nameProperty());
            ageField.textProperty().bindBidirectional(student.ageProperty(), new NumberStringConverter());
            gradeField.textProperty().bindBidirectional(student.gradeProperty());
        }
    }

    @FXML
    private void onSave() {
        viewModel.saveStudent();
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
        // Logic to close the edit window and refresh the list view
    }
}

