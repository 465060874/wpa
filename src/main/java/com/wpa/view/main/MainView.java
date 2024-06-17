package com.wpa.view.main;

import com.wpa.model.CodeNamePair;
import com.wpa.model.category.Category;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.util.List;

public class MainView implements FxmlView<MainViewModel> {
    @InjectViewModel
    private MainViewModel viewModel;

    @FXML
    private SplitPane splitPane;

    @FXML
    private TreeView<CodeNamePair> categoryTree;

    public void initialize() {
        // Initialize the view model here.
        System.out.println("Initializing MainView");
        List<Category> categories = Category.getCategories();

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
                        System.out.println("updateItem");
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
    }

    public SplitPane getSplitPane() {
        return splitPane;
    }
}
