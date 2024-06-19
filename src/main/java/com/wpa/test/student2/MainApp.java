package com.wpa.test.student2;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StudentScope scope = new StudentScope();
        // 初始化学生数据
        scope.getStudents().add(new Student("Alice", 20, "Senior"));
        scope.getStudents().add(new Student("Bob", 21, "Junior"));

        ViewTuple<StudentListView, StudentListViewModel> listViewTuple = FluentViewLoader.fxmlView(StudentListView.class)
                .providedScopes(scope)
                .load();

        primaryStage.setScene(new Scene(listViewTuple.getView()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
