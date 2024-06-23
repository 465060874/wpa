package com.wpa.page;

import atlantafx.base.controls.Spacer;
import atlantafx.base.theme.Styles;
import atlantafx.base.theme.Tweaks;
import com.wpa.layout.ApplicationWindow;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2AL;

import javax.swing.*;
import java.util.Objects;
import java.util.Random;

import static javafx.scene.input.KeyCombination.ALT_DOWN;
import static javafx.scene.shape.DrawMode.FILL;

public interface Page {
    int MAX_WIDTH = ApplicationWindow.MIN_WIDTH - ApplicationWindow.SIDEBAR_WIDTH;
    int HGAP_20 = 20;
    int HGAP_30 = 30;
    int VGAP_10 = 10;
    int VGAP_20 = 20;

    default String getName(){
        return "Not Defined";
    };

    Parent getView();

    void buildView(String header);

    ///////////////////////////////////////////////////////////////////////////

    class PageHeader extends HBox {

        public PageHeader(Page page) {
            super();

            Objects.requireNonNull(page, "page");

            var titleLbl = new Label(page.getName());
            titleLbl.getStyleClass().add(Styles.TITLE_2);

//            var sourceCodeItem = new MenuItem("Source Code", new FontIcon(Feather.CODE));
//            sourceCodeItem.setDisable(!page.canDisplaySourceCode());
//            sourceCodeItem.setAccelerator(new KeyCodeCombination(KeyCode.C, ALT_DOWN));
//            sourceCodeItem.setOnAction(e ->
//                    DefaultEventBus.getInstance().publish(new PageEvent(PageEvent.Action.SOURCE_CODE_ON))
//            );
//
//            final var uri = page.getJavadocUri();
//            var javadocItem = new MenuItem("Javadoc", new FontIcon(Feather.COFFEE));
//            javadocItem.setAccelerator(new KeyCodeCombination(KeyCode.J, ALT_DOWN));
//            javadocItem.setDisable(uri == null);
//            javadocItem.setOnAction(e -> {
//                if (uri != null) {
//                    DefaultEventBus.getInstance().publish(new BrowseEvent(uri));
//                }
//            });
//
//            var menuBtn = new MenuButton(null, new FontIcon(Material2AL.EXPAND_MORE));
//            menuBtn.getStyleClass().addAll(Styles.FLAT, Styles.BUTTON_ICON, Tweaks.NO_ARROW);
//            menuBtn.getItems().setAll(sourceCodeItem, javadocItem);
            CornerRadii cornerRadii = new CornerRadii(10);
            setBorder(new Border(new BorderStroke(Color.GREEN,
                    BorderStrokeStyle.SOLID, cornerRadii, BorderWidths.DEFAULT)));
            getStyleClass().add("header");
            setSpacing(200);
            getChildren().setAll(titleLbl);

        }
    }
}
