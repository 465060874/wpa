package com.wpa;

import com.wpa.mvvm.MvvmfxSpringApplication;
import com.wpa.ui.main.ApplicationWindowView;
import com.wpa.ui.main.ApplicationWindowViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
//    @Override
//    public void initMvvmfx() throws Exception {
//        super.initMvvmfx();
//    }
//
//    @Override
//    public void startMvvmfx(Stage stage) throws Exception {
//        ViewTuple<ApplicationWindowView, ApplicationWindowViewModel> main = FluentViewLoader.fxmlView(ApplicationWindowView.class).load();
//
//        Scene rootScene = new Scene(main.getView());
//
////        rootScene.getStylesheets().add("/contacts.css");
//
//        stage.setScene(rootScene);
//        stage.show();
//    }
//
//    @Override
//    public void stopMvvmfx() throws Exception {
//        super.stopMvvmfx();
//    }

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        final Parent parent = FluentViewLoader.fxmlView(ApplicationWindowView.class).load().getView();

        stage.setScene(new Scene(parent));
        stage.show();
    }
}
