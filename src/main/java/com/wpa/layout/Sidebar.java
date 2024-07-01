package com.wpa.layout;

import atlantafx.base.controls.Spacer;
import atlantafx.base.theme.Styles;
import com.wpa.model.CodeNamePair;
import com.wpa.model.category.Category;
import com.wpa.page.BusinessPage;
import com.wpa.page.PageLoader;
import com.wpa.util.Resources;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2MZ;
import org.kordamp.ikonli.material2.Material2OutlinedAL;

import java.util.List;

import static atlantafx.base.theme.Styles.*;

public class Sidebar extends VBox {

    private PageLoader loader;

    public void setPageLoader(PageLoader loader) {
        this.loader = loader;
    }

    public Sidebar() {
        super();
        initialize();
    }
    public void initialize(){
        createView();
    }

    private void createView() {
        var header = new Header();

        TreeView navTree = createNavTree();
        VBox.setVgrow(navTree, Priority.ALWAYS);

        setId("sidebar");
        getChildren().addAll(header, navTree, createFooter());
    }

    private TreeView createNavTree() {
        List<Category> categories = Category.getCategories();
        TreeView<CodeNamePair> categoryTree = new TreeView<>();

        TreeItem<CodeNamePair> rootItem = new TreeItem(new CodeNamePair("wph", "工作知识库"));

        for (Category category : categories) {
            TreeItem categoryItem = new TreeItem(category.getParent().copy());
            for (CodeNamePair child : category.getChildren()) {
                categoryItem.getChildren().add(new TreeItem(child.copy()));
            }
            rootItem.getChildren().add(categoryItem);
        }

        categoryTree.setRoot(rootItem);

        categoryTree.setCellFactory(new Callback<>() {
            @Override
            public TreeCell<CodeNamePair> call(TreeView<CodeNamePair> param) {
                return new TreeCell<>() {
                    @Override
                    protected void updateItem(CodeNamePair item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });

        categoryTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItem) -> {
            loader.loadPage(BusinessPage.class,newItem.getValue().getCode());
        });
        return categoryTree;
    }


    private HBox createFooter() {
        var footer = new HBox(new Label("version"));
        footer.getStyleClass().add("footer");
        return footer;
    }

    private class Header extends VBox {
        public Header() {
            super();
            getStyleClass().add("header");
            getChildren().setAll(createSearchButton()
            );
        }

        private HBox createLogo() {
            var image = new ImageView(
                    new Image(Resources.getResource("assets/app-icon.png").toString())
            );
            image.setFitWidth(32);
            image.setFitHeight(32);

            var imageBorder = new Insets(1);
            var imageBox = new StackPane(image);
            imageBox.getStyleClass().add("image");
            imageBox.setPadding(imageBorder);
            imageBox.setPrefSize(
                    image.getFitWidth() + imageBorder.getRight() * 2,
                    image.getFitWidth() + imageBorder.getTop() * 2
            );
            imageBox.setMaxSize(
                    image.getFitHeight() + imageBorder.getTop() * 2,
                    image.getFitHeight() + imageBorder.getRight() * 2
            );

            var titleLbl = new Label("AtlantaFX");
            titleLbl.getStyleClass().addAll(TITLE_3);
            if (true) {
                var devLabel = new Label();
                devLabel.setGraphic(new FontIcon(Material2OutlinedAL.INFO));
                devLabel.getStyleClass().addAll("dev-indicator", Styles.WARNING);
                devLabel.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                devLabel.setTooltip(new Tooltip("App is running in development mode"));

                titleLbl.setContentDisplay(ContentDisplay.RIGHT);
                titleLbl.setGraphic(devLabel);
            }

            var themeSwitchBtn = new Button();
            themeSwitchBtn.getStyleClass().add("palette");
            themeSwitchBtn.setGraphic(new FontIcon(Material2MZ.WB_SUNNY));
            themeSwitchBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            themeSwitchBtn.getStyleClass().addAll(Styles.BUTTON_CIRCLE, Styles.FLAT);
            themeSwitchBtn.setAlignment(Pos.CENTER_RIGHT);
            //themeSwitchBtn.setOnAction(e -> openThemeDialog());

            var root = new HBox(10, imageBox, titleLbl, new Spacer(), themeSwitchBtn);
            root.getStyleClass().add("logo");
            root.setAlignment(Pos.CENTER_LEFT);

            return root;
        }

        private Button createSearchButton() {
            var titleLbl = new Label("Search", new FontIcon(Material2MZ.SEARCH));

            var hintLbl = new Label("Press /");
            hintLbl.getStyleClass().addAll("hint", TEXT_MUTED, TEXT_SMALL);

            var searchBox = new HBox(titleLbl, new Spacer(), hintLbl);
            searchBox.getStyleClass().add("content");
            searchBox.setAlignment(Pos.CENTER_LEFT);

            var root = new Button();
            root.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            root.getStyleClass().addAll("search-button");
            root.setGraphic(searchBox);
            //root.setOnAction(e -> openSearchDialog());
            root.setMaxWidth(Double.MAX_VALUE);

            return root;
        }
    }
}
