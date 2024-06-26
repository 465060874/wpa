package com.wpa.test.student2;

import com.wpa.test.student2.*;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StudentListView implements FxmlView<StudentListViewModel> {
    @InjectViewModel
    private StudentListViewModel viewModel;

    @Autowired
    private StudentListViewModel viewModel1;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    public void initialize() {
        studentTable.setItems(viewModel.getStudents());


        TableColumn<Student, Void> editColumn = new TableColumn<>("Edit");
        editColumn.setCellFactory(new Callback<TableColumn<Student, Void>, TableCell<Student, Void>>() {
            @Override
            public TableCell<Student, Void> call(TableColumn<Student, Void> param) {
                return new TableCell<Student, Void>() {
                    private final Button editButton = new Button("Edit");

                    {
                        editButton.setOnAction(event -> {
                            Student student = getTableView().getItems().get(getIndex());
                            if (student != null) {
                                openEditStudentView(student);
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(editButton);
                        }
                    }
                };
            }
        });

        studentTable.getColumns().add(editColumn);
    }

    public void openEditStudentView(Student student) {
        viewModel.editStudent(student);
        ViewTuple<EditStudentView, EditStudentViewModel> editViewTuple = FluentViewLoader.fxmlView(EditStudentView.class)
//                .providedScopes(viewModel.getStudentScope()) // 获取scope实例
                .load();
        Stage stage = new Stage();
        stage.setScene(new Scene(editViewTuple.getView()));
        stage.show();
    }
}