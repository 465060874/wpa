package com.wpa;

import com.wpa.config.AppConfig;
import com.wpa.mvvm.MvvmfxCdiApplication;
import com.wpa.view.main.MainView;
import com.wpa.view.main.MainViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javafx.application.HostServices;

public class App extends MvvmfxCdiApplication {
    private static ApplicationContext context;

    public static void main(String... args) {
        Application.setUserAgentStylesheet("/themes/primer-light.css");
        Application.launch(args);
    }

//    @Override
//    public void init() throws Exception {
//        super.init();
//        context = new AnnotationConfigApplicationContext(AppConfig.class);
//    }

//    @Override
//    public void start(Stage stage) throws Exception {
//        initUI(stage);
//    }

    //    @Override
//    public void stop() throws Exception {
//        // 关闭Spring容器
//        ((ConfigurableApplicationContext) context).close();
//    }

    private void adjustDividerPosition(SplitPane splitPane) {
        double dividerPosition = 0.2; // 左边面板占1/6,右边面板占5/6
        splitPane.setDividerPositions(dividerPosition);
    }



    @Override
    public void startMvvmfx(Stage stage) throws Exception {
        super.init();
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        initUI(stage);
    }

    @Override
    public void stopMvvmfx() throws Exception {
        super.stopMvvmfx();
    }

    private void initUI(Stage stage){
        //        Application.setUserAgentStylesheet("/themes/dracula.css");
        ViewTuple<MainView, MainViewModel> main = FluentViewLoader.fxmlView(MainView.class).load();

        Scene rootScene = new Scene(main.getView());
        String sheet = BootstrapFX.bootstrapFXStylesheet();
        rootScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // 窗口最大化时执行的代码
                main.getCodeBehind().getSplitPane().setDividerPositions(0.16666667); // 设置分隔线位置,左边面板占1/6,右边面板占5/6
            }
        });
        stage.setScene(rootScene);
        stage.show();

        SplitPane splitPane = main.getCodeBehind().getSplitPane();


        ReadOnlyDoubleWrapper windowWidth = new ReadOnlyDoubleWrapper(stage.getWidth());
        ReadOnlyDoubleWrapper windowHeight = new ReadOnlyDoubleWrapper(stage.getHeight());

        windowWidth.addListener((observable, oldValue, newValue) -> adjustDividerPosition(splitPane));
        windowHeight.addListener((observable, oldValue, newValue) -> adjustDividerPosition(splitPane));

        ReadOnlyDoubleWrapper splitPaneWidth = new ReadOnlyDoubleWrapper(splitPane.getWidth());
        ReadOnlyDoubleWrapper splitPaneHeight = new ReadOnlyDoubleWrapper(splitPane.getHeight());

        splitPaneWidth.addListener((observable, oldValue, newValue) -> adjustDividerPosition(splitPane));
        splitPaneHeight.addListener((observable, oldValue, newValue) -> adjustDividerPosition(splitPane));

        // 监听窗口最大化和还原的事件
        stage.maximizedProperty().addListener((obs, wasMaximized, isNowMaximized) -> {
            if (isNowMaximized) {
                // 窗口最大化时，设置左右两边的比例为1:5
                splitPane.setDividerPositions(0.16666666667);
            } else {
                // 窗口还原时，设置左右两边的比例为1:5
                splitPane.setDividerPositions(0.16666666667);
            }
        });

        // 监听窗口大小变化的事件
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            adjustDividerPosition(splitPane);
        });
    }
}
