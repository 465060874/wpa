package com.wpa.test.student2;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class EditStudentView implements FxmlView<EditStudentViewModel> {
    @InjectViewModel
    private EditStudentViewModel viewModel;

    @Autowired
    private EditStudentViewModel viewModel1;

    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField gradeField;

    @FXML
    private javafx.scene.control.Button saveButton;

    public void initialize() {
        Student student = viewModel.getStudent();
        if (student != null) {
            nameField.textProperty().bindBidirectional(student.nameProperty());
            ageField.textProperty().bindBidirectional(student.ageProperty(), new NumberStringConverter());
            gradeField.textProperty().bindBidirectional(student.gradeProperty());
        }
    }

    @FXML
    private void saveStudent() {
        viewModel.saveStudent();
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
        // 关闭编辑窗口或返回主界面
    }
}
