package com.wpa.page;

import atlantafx.base.controls.Spacer;
import atlantafx.base.util.BBCodeParser;
import com.wpa.util.NodeUtils;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URI;

import static javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER;

public abstract class AbstractPage extends StackPane implements Page {
    protected String name;
    protected final VBox userContent = new VBox();
    protected final StackPane userContentArea = new StackPane(userContent);
    protected boolean isRendered = false;


    @Override
    public void buildView(String header) {
        name = header;
        userContent.getStyleClass().add("user-content");
        getStyleClass().add("page");
        createPageLayout();
        addPageHeader();
        Spacer spacer1 = new Spacer();
        spacer1.setStyle("-fx-background-color: rgba(255,192,203,0.3);");
        addNode(spacer1);
        addNode(buildContentNode());

    }

    abstract Node buildContentNode();

    protected AbstractPage() {
        super();
    }

    protected void createPageLayout() {
//        userContentArea.setAlignment(Pos.TOP_CENTER);
//        userContent.setMinWidth(Math.min(Page.MAX_WIDTH, 800));
//        userContent.setMaxWidth(Math.min(Page.MAX_WIDTH, 800));

        var scrollPane = new ScrollPane(userContentArea);
//        NodeUtils.setScrollConstraints(scrollPane, AS_NEEDED, true, NEVER, true);
//        scrollPane.setMaxHeight(20_000);

        getChildren().setAll(scrollPane);

        // 设置ScrollPane占满整个StackPane
        StackPane.setMargin(scrollPane, new javafx.geometry.Insets(10)); // 可选：设置边距
//        stackPane.setPrefSize(600, 400); // 设置StackPane的首选大小
        scrollPane.setFitToWidth(true);  // 设置ScrollPane宽度自动适应内容宽度
        scrollPane.setFitToHeight(true); // 设置ScrollPane高度自动适应内容高度
    }

    @Override
    public Pane getView() {
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        if (isRendered) {
            return;
        }

        isRendered = true;
        onRendered();
    }

    // Some properties can only be obtained after node placed
    // to the scene graph and here is the place do this.
    protected void onRendered() {
    }

    protected void addPageHeader() {
        var pageHeader = new PageHeader(this);
        userContent.getChildren().add(pageHeader);
    }

    protected void addNode(Node node) {
        userContent.getChildren().add(node);
    }

    protected void addFormattedText(String text) {
        userContent.getChildren().add(BBCodeParser.createFormattedText(text));
    }
}
