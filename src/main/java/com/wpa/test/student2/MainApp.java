package com.wpa.test.student2;

import com.wpa.config.AppConfig;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp extends Application {
    private static ApplicationContext springContext;

    @Override
    public void init() throws Exception {
        springContext = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Application.setUserAgentStylesheet("/themes/primer-light.css");
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
