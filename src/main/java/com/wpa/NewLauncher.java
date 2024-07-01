package com.wpa;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import com.wpa.config.AppConfig;
import com.wpa.database.DatabaseInitializer;
import com.wpa.layout.ApplicationWindow;
import com.wpa.mvvm.MvvmfxSpringApplication;
import com.wpa.theme.ThemeManager;
import com.wpa.util.Resources;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class NewLauncher extends Application {

    private static ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = new AnnotationConfigApplicationContext(AppConfig.class);
        DatabaseInitializer.initDatabase();
    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static ConfigurableApplicationContext getSpringContext() {
        return springContext;
    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
//        Thread.currentThread().setUncaughtExceptionHandler(new DefaultExceptionHandler(stage));
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
//        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
//        loadApplicationProperties();
//
//        if (IS_DEV_MODE) {
//            System.out.println("[WARNING] Application is running in development mode.");
//        }

        var root = new ApplicationWindow();

        var antialiasing = Platform.isSupported(ConditionalFeature.SCENE3D)
                ? SceneAntialiasing.BALANCED
                : SceneAntialiasing.DISABLED;
        var scene = new Scene(root, ApplicationWindow.MIN_WIDTH + 80, 768, false, antialiasing);

//        Application.setUserAgentStylesheet("themes/primer-light.css");
//        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
//        scene.getStylesheets().addAll(Resources.resolve("assets/styles/index.css"));

//        var tm = ThemeManager.getInstance();
//        tm.setScene(scene);
//        tm.setTheme(tm.getDefaultTheme());

        stage.setScene(scene);
//        stage.setTitle(System.getProperty("app.name"));
        stage.setResizable(true);
        stage.setOnCloseRequest(t -> Platform.exit());
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        // register event listeners

        Platform.runLater(() -> {
            stage.show();
            stage.requestFocus();
        });
    }

    ////////////////////////////////////////////////////////////////////////////
    protected ConfigurableApplicationContext initSpringContext(){
        return new AnnotationConfigApplicationContext(AppConfig.class);
    }

//    @Override
    public void startMvvmfx(Stage stage) throws Exception {
                Thread.currentThread().setUncaughtExceptionHandler(new DefaultExceptionHandler(stage));
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
//        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
//        loadApplicationProperties();
//
//        if (IS_DEV_MODE) {
//            System.out.println("[WARNING] Application is running in development mode.");
//        }

        var root = new ApplicationWindow();

        var antialiasing = Platform.isSupported(ConditionalFeature.SCENE3D)
                ? SceneAntialiasing.BALANCED
                : SceneAntialiasing.DISABLED;
        var scene = new Scene(root, ApplicationWindow.MIN_WIDTH + 80, 768, false, antialiasing);

//        Application.setUserAgentStylesheet("themes/primer-light.css");
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
//        scene.getStylesheets().addAll(Resources.resolve("assets/styles/index.css"));

//        var tm = ThemeManager.getInstance();
//        tm.setScene(scene);
//        tm.setTheme(tm.getDefaultTheme());

        stage.setScene(scene);
//        stage.setTitle(System.getProperty("app.name"));
        stage.setResizable(true);
        stage.setOnCloseRequest(t -> Platform.exit());

        // register event listeners

        Platform.runLater(() -> {
            stage.show();
            stage.requestFocus();
        });
    }
}
