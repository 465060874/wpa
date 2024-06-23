package com.wpa.layout;

import com.wpa.NewLauncher;
import com.wpa.config.SpringBeanInjector;
import com.wpa.page.HtmlEditorPage;
import com.wpa.page.Page;
import javafx.animation.FadeTransition;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Objects;

import static javafx.scene.layout.Priority.ALWAYS;

public class MainLayer extends BorderPane {
    private final Sidebar sidebar = new Sidebar();
    private final StackPane contentPane = new StackPane();

    public MainLayer() {
        sidebar.setPageLoader(this::loadPage);
        createView();
        loadPage(HtmlEditorPage.class,"HTMLEditorPage");
    }



    private void createView() {
        sidebar.setMinWidth(ApplicationWindow.SIDEBAR_WIDTH);
        sidebar.setMaxWidth(ApplicationWindow.SIDEBAR_WIDTH);

//        codeViewer = new CodeViewer();
//
//        codeViewerWrapper = new StackPane();
//        codeViewerWrapper.getStyleClass().add("source-code");
//        codeViewerWrapper.getChildren().setAll(codeViewer);
//
//        contentPane.getChildren().setAll(codeViewerWrapper);
        HBox.setHgrow(contentPane, ALWAYS);

        // ~

        setId("main");
        //setTop(headerBar);
        setLeft(sidebar);
        setCenter(contentPane);
    }

    public void loadPage(Class<? extends Page> pageClass, String name) {
        try {
            contentPane.getChildren().clear();
//            final Page prevPage = (Page) contentPane.getChildren().stream()
//                    .filter(c -> c instanceof Page)
//                    .findFirst()
//                    .orElse(null);
            Page page = NewLauncher.getSpringContext().getBean(pageClass);
            page.buildView(name);
//            final Page nextPage = pageClass.getDeclaredConstructor().newInstance();
//            nextPage.buildView(name);
//            SpringBeanInjector injector = NewLauncher.getSpringContext().getBean(SpringBeanInjector.class);
//            injector.inject(nextPage);

//            // startup, no prev page, no animation
//            if (getScene() == null) {
//                subLayerPane.getChildren().add(nextPage.getView());
//                return;
//            }
//
//            Objects.requireNonNull(prevPage);

            // reset previous page, e.g. to free resources
//            prevPage.reset();

            // animate switching between pages
//            if(prevPage !=null) {
//                contentPane.getChildren().remove(prevPage.getView());
//            }
            contentPane.getChildren().add(page.getView());
//            var transition = new FadeTransition(Duration.millis(PAGE_TRANSITION_DURATION), nextPage.getView());
//            transition.setFromValue(0.0);
//            transition.setToValue(1.0);
//            transition.setOnFinished(t -> {
//                if (nextPage instanceof Pane nextPane) {
//                    nextPane.toFront();
//                }
//            });
//            transition.play();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
