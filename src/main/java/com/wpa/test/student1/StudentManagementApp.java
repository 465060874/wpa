package com.wpa.test.student1;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
public class StudentManagementApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Application.setUserAgentStylesheet("/themes/nord-light.css");
        // Create the global scope
        StudentScope studentScope = new StudentScope();

        // Load the main view with the provided scope
        ViewTuple<StudentListView, StudentListViewModel> viewTuple = FluentViewLoader.fxmlView(StudentListView.class)
                .providedScopes(studentScope)
                .load();

        // Set the scene and show the primary stage
        Scene scene = new Scene(viewTuple.getView());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
