package com.wpa.test.student2;

import com.wpa.config.AppConfig;
import com.wpa.mvvm.MvvmfxSpringApplication;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


public class MainApp extends MvvmfxSpringApplication {
    private static ApplicationContext springContext;



//    @Override
//    public void init() throws Exception {
//        springContext = new AnnotationConfigApplicationContext(AppConfig.class);
//    }


//    public static ApplicationContext getSpringContext() {
//        return new AnnotationConfigApplicationContext(AppConfig.class);
//    }

    protected ConfigurableApplicationContext initSpringContext(){
        return new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Override
    public void startMvvmfx(Stage primaryStage) throws Exception {
        Application.setUserAgentStylesheet("/themes/primer-light.css");

        /**
         * 如果需要使用scope，那么需要初始化它并将它传递给fluentViewLoader，即providedScopes里头
         */
        StudentScope scope = new StudentScope();
        // 初始化学生数据
//        scope.getStudents().add(new Student("Alice", 20, "Senior"));
//        scope.getStudents().add(new Student("Bob", 21, "Junior"));
//        ViewTuple<StudentListView, StudentListViewModel> listViewTuple = FluentViewLoader.fxmlView(StudentListView.class)
//                .providedScopes(scope)
//                .load();



        ViewTuple<StudentListView, StudentListViewModel> listViewTuple = FluentViewLoader.fxmlView(StudentListView.class)
                .load();

        primaryStage.setScene(new Scene(listViewTuple.getView()));
        primaryStage.show();
    }

    //@Override
//    public void start(Stage primaryStage) throws Exception {
//        Application.setUserAgentStylesheet("/themes/primer-light.css");
//        StudentScope scope = new StudentScope();
//        // 初始化学生数据
//        scope.getStudents().add(new Student("Alice", 20, "Senior"));
//        scope.getStudents().add(new Student("Bob", 21, "Junior"));
//
//        ViewTuple<StudentListView, StudentListViewModel> listViewTuple = FluentViewLoader.fxmlView(StudentListView.class)
//                .providedScopes(scope)
//                .load();
//
//        primaryStage.setScene(new Scene(listViewTuple.getView()));
//        primaryStage.show();
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
