package com.wpa.view.main;

import atlantafx.base.theme.Styles;
import com.wpa.dto.Criteria;
import com.wpa.model.CodeNamePair;
import com.wpa.model.category.Category;
import com.wpa.view.business.BusinessView;
import com.wpa.view.business.BusinessViewModel;
import de.saxsys.mvvmfx.*;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import javafx.util.Callback;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainView implements FxmlView<MainViewModel> {
    @InjectViewModel
    private MainViewModel viewModel;

    @FXML
    private SplitPane splitPane;

    @FXML
    private TreeView<CodeNamePair> categoryTree;

    @FXML
    private TabPane tableTabPane;

    @Inject
    private Stage primaryStage;

    private Map<String, Integer> linkWithIndex = new HashMap<>();

    private static final Map<String, String> LINK_MAPS = new HashMap<>();
    static {
        LINK_MAPS.put("jenkov", "https://jenkov.com/tutorials/javafx/index.html");
        LINK_MAPS.put("controlsfx", "https://github.com/controlsfx/controlsfx");
        LINK_MAPS.put("FormsFX", "https://github.com/dlsc-software-consulting-gmbh/FormsFX");
        LINK_MAPS.put("bootstrapfx", "https://github.com/kordamp/bootstrapfx");
        LINK_MAPS.put("AtlantaFX", "https://github.com/mkpaz/atlantafx");
        LINK_MAPS.put("CSPROD", "https://www.cargosmart.com/admin/internalsupport/is_sign_in.jsf");

    }

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
            updateTab(newItem.getValue());
        });
    }

    public SplitPane getSplitPane() {
        return splitPane;
    }


    private void updateTab(CodeNamePair menuItem) {
        if(LINK_MAPS.containsKey(menuItem.getCode())){
            try {
                int tabCount = tableTabPane.getTabs().size();
                if(linkWithIndex.containsKey(menuItem.getCode())){
                    tableTabPane.getSelectionModel().select(linkWithIndex.get(menuItem.getCode()));
//                    tabPane.getTabs().get(linkWithIndex.get(menuItem.getCode()));
                }else {
                    WebView webView = new WebView();
                    WebEngine webEngine = webView.getEngine();
                    webEngine.load(LINK_MAPS.get(menuItem.getCode()));
                    Tab tab = new Tab(menuItem.getName(), webView);
                    tab.setClosable(true);
                    tableTabPane.getTabs().add(tabCount, tab);
                    tableTabPane.getSelectionModel().select(tabCount);
                    linkWithIndex.put(menuItem.getCode(), tabCount);
                }
                // 打开指定的 URL
//                Desktop.getDesktop().browse(new URI(LINK_MAPS.get(menuItem.getCode())));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        tableTabPane.getTabs().get(0).setText(menuItem.getName());

        Parent root = null;
        if("br_message".equalsIgnoreCase(menuItem.getCode())){
            BusinessViewModel businessViewModel =new BusinessViewModel(menuItem.getCode());

            ViewTuple<BusinessView, BusinessViewModel> load = FluentViewLoader
                    .fxmlView(BusinessView.class).viewModel(businessViewModel)
                    .load();
            root = load.getView();

        }else {
//            KnowledgeEntryTableView entryTableView = new KnowledgeEntryTableView();
//            TableView<KnowledgeEntryDTO> knowledgeEntryTableView = entryTableView.getTableView();
//            Styles.toggleStyleClass(knowledgeEntryTableView, Styles.BORDERED);
//            Styles.toggleStyleClass(knowledgeEntryTableView, Styles.STRIPED);
//            root = new StackPane(knowledgeEntryTableView);
        }
        tableTabPane.getTabs().get(0).setContent(root);
        tableTabPane.getSelectionModel().select(tableTabPane.getTabs().get(0));



    }

    public static BuilderFactory customBuilderFactory = type -> {
        if(Criteria.class.isAssignableFrom(type)) {
            return (Builder<Criteria>) () -> new Criteria("Test 1");
        } else {
            return null;
        }
    };
}
