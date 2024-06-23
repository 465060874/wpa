package com.wpa.test.employee;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class EmployeeMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        EmployeeHandler employeeHandler = new EmployeeHandler(webEngine);
//        JSObject window = (JSObject) webEngine.executeScript("window");
//        window.setMember("java", employeeHandler);

        webEngine.load(getClass().getResource("/html/employees.html").toExternalForm());

        // 等待页面加载完成后绑定
        webEngine.documentProperty().addListener((obs, oldDoc, newDoc) -> {
            if (newDoc != null) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("java", employeeHandler);
            }

    });

        BorderPane root = new BorderPane();
        root.setCenter(webView);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Employee Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}