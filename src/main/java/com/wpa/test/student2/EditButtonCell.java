package com.wpa.test.student2;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EditButtonCell extends TableCell<Student, Void> {
    private final Button editButton = new Button("Edit");

    public EditButtonCell() {
        editButton.setOnAction(event -> {
            Student student = getTableView().getItems().get(getIndex());
            if (student != null) {
                // 打开编辑界面
                ViewTuple<EditStudentView, EditStudentViewModel> editViewTuple = FluentViewLoader.fxmlView(EditStudentView.class)
                        //.providedScopes(getScope()) // 获取scope实例
                        .load();
                Stage stage = new Stage();
                stage.setScene(new Scene(editViewTuple.getView()));
                stage.show();
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
}
