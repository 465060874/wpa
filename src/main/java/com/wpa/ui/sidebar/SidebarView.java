package com.wpa.ui.sidebar;

import com.wpa.model.CodeNamePair;
import com.wpa.model.category.Category;
import com.wpa.page.BusinessPage;
import de.saxsys.mvvmfx.FxmlView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

import java.util.List;

public class SidebarView implements FxmlView<SidebarViewModel> {
    @FXML
    private TreeView<CodeNamePair> treeView;

    @FXML
    private Label label;

    public void initialize() {
        List<Category> categories = Category.getCategories();

        TreeItem<CodeNamePair> rootItem = new TreeItem(new CodeNamePair("wph", "工作知识库"));

        for (Category category : categories) {
            TreeItem categoryItem = new TreeItem(category.getParent().copy());
            for (CodeNamePair child : category.getChildren()) {
                categoryItem.getChildren().add(new TreeItem(child.copy()));
            }
            rootItem.getChildren().add(categoryItem);
        }

        treeView.setRoot(rootItem);

        treeView.setCellFactory(new Callback<>() {
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
//        createNavTree();
        label.setText("Sidebar");
    }

    private TreeView createNavTree() {
        List<Category> categories = Category.getCategories();
        treeView = new TreeView<>();

        TreeItem<CodeNamePair> rootItem = new TreeItem(new CodeNamePair("wph", "工作知识库"));

        for (Category category : categories) {
            TreeItem categoryItem = new TreeItem(category.getParent().copy());
            for (CodeNamePair child : category.getChildren()) {
                categoryItem.getChildren().add(new TreeItem(child.copy()));
            }
            rootItem.getChildren().add(categoryItem);
        }

        treeView.setRoot(rootItem);

        treeView.setCellFactory(new Callback<>() {
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

//        categoryTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItem) -> {
//            loader.loadPage(BusinessPage.class,newItem.getValue().getCode());
//        });
        return treeView;
    }
}
