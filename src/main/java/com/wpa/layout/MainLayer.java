package com.wpa.layout;

import com.wpa.NewLauncher;
import com.wpa.page.HtmlEditorPage;
import com.wpa.page.Page;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import static javafx.scene.layout.Priority.ALWAYS;

public class MainLayer extends BorderPane {
    private final StackPane contentPane = new StackPane();
    private final Sidebar sidebar = new Sidebar();

    public MainLayer() {
        sidebar.setPageLoader(this::loadPage);
        createView();
        loadPage(HtmlEditorPage.class,"HTMLEditorPage");
    }



    private void createView() {
        sidebar.setMinWidth(ApplicationWindow.SIDEBAR_WIDTH);
        sidebar.setMaxWidth(ApplicationWindow.SIDEBAR_WIDTH);
        HBox.setHgrow(contentPane, ALWAYS);
        setId("main");
        setLeft(sidebar);
        setCenter(contentPane);
    }

    public void loadPage(Class<? extends Page> pageClass, String name) {
        try {
            contentPane.getChildren().clear();
            Page page = NewLauncher.getSpringContext().getBean(pageClass);
            page.buildView(name);
            contentPane.getChildren().add(page.getView());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
