package com.wpa.test.student1;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StudentListView implements FxmlView<StudentListViewModel> {
    @FXML
    private TableView<Student1> studentTable;
    @FXML
    private TableColumn<Student1, String> nameColumn;
    @FXML
    private TableColumn<Student1, Integer> ageColumn;
    @FXML
    private TableColumn<Student1, String> gradeColumn;
    @FXML
    private TableColumn<Student1, Void> editColumn;

    @InjectViewModel
    private StudentListViewModel viewModel;

    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());

        Callback<TableColumn<Student1, Void>, TableCell<Student1, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Student1, Void> call(final TableColumn<Student1, Void> param) {
                final TableCell<Student1, Void> cell = new TableCell<>() {
                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Student1 student1 = getTableView().getItems().get(getIndex());
                            viewModel.editStudent(student1);
                            openStudentEditView();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        editColumn.setCellFactory(cellFactory);

        studentTable.setItems(viewModel.getStudents());
    }

    private void openStudentEditView() {
        // Load and display the StudentEditView
        ViewTuple<StudentEditView, StudentEditViewModel> viewTuple = FluentViewLoader.fxmlView(StudentEditView.class)
                .providedScopes(viewModel.getStudentScope())
                .load();
        Stage stage = new Stage();
        stage.setScene(new Scene(viewTuple.getView()));
        stage.show();
    }
}

