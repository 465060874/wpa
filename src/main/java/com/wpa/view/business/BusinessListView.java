package com.wpa.view.business;

import com.wpa.dto.Criteria;
import com.wpa.repository.BusinessRepository;
import com.wpa.service.BusinessService;
import de.saxsys.mvvmfx.Context;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectContext;
import de.saxsys.mvvmfx.InjectViewModel;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class BusinessListView implements FxmlView<BusinessListViewModel> {

    @Autowired
    private BusinessService businessService;

//    @Autowired
//    private BusinessRepository businessRepository;

    @FXML
    private TableView<BusinessTableViewModel> businessTable;
    private String category;

    private Criteria criteria;

    @InjectContext
    private Context context;

    @InjectViewModel
    private BusinessListViewModel viewModel;

    @FXML
    TableColumn<BusinessTableViewModel, String> contentColumn;

    @FXML
    TableColumn<BusinessTableViewModel, Void> actionColumn;

    public void initialize() {
        System.out.println("BusinessView initialized=" + category);
        businessTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        viewModel.initialize();

        // 设置自定义单元格工厂
        contentColumn.setCellFactory(new Callback<TableColumn<BusinessTableViewModel, String>, TableCell<BusinessTableViewModel, String>>() {
            @Override
            public TableCell<BusinessTableViewModel, String> call(TableColumn<BusinessTableViewModel, String> param) {
                return new CopyableTableCell();
            }
        });

        actionColumn.setCellFactory(param -> new TableCell<>() {
            //            private final Button editButton = new Button("Edit");
            private final Hyperlink editButton = new Hyperlink("编辑");
            {
                editButton.getStyleClass().setAll("btn","btn-info");
                editButton.setMnemonicParsing(true);
                editButton.setOnAction(event -> {
                    BusinessTableViewModel businessTableViewModel = getTableView().getItems().get(getIndex());
                    viewModel.setSelectedTableRowViewModel(businessTableViewModel);
                    showEditForm();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });

        businessTable.setItems(viewModel.getBusinessList());
        System.out.println("BusinessView initialized2");
//        businessTable.setItems(viewModel.getContactList());

//        viewModel.selectedTableRowProperty().bind(contactTable.getSelectionModel().selectedItemProperty());
//
//        // When the selectedTableRowProperty changes in the viewModel we need to update the table
//        viewModel.setOnSelect(vm -> contactTable.getSelectionModel().select(vm));
    }

    public void setCategory(String category) {
        this.category = category;
    }


    private class CopyableTableCell extends TableCell<BusinessTableViewModel, String> {
        private HBox hbox;
        private Label textLabel;
        private Button copyButton;
        private String content;


        public CopyableTableCell() {
            hbox = new HBox();
            textLabel = new Label();
            textLabel.setMinWidth(150); // 设置最小宽度
            textLabel.setPrefWidth(150); // 设置首选宽度
            textLabel.setMaxWidth(150); // 设置最大宽度

            // 使用 Ikonli 创建包含图标的按钮
            FontIcon copyIcon = new FontIcon(FontAwesomeSolid.COPY);
            copyButton = new Button("", copyIcon);
            // 自定义按钮样式
            copyButton.setStyle("-fx-background-color: transparent; " +
                    "-fx-border-color: transparent; " +
                    "-fx-text-fill: blue; " +
                    "-fx-underline: true;");
            // 添加悬停效果和手形光标
            copyButton.setOnMouseEntered(event -> {
                copyButton.setStyle("-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; " +
                        "-fx-text-fill: darkblue; " + // 悬停时改变文本颜色
                        "-fx-underline: true;");
                copyButton.setCursor(Cursor.HAND); // 设置手形光标
            });

            copyButton.setOnMouseExited(event -> {
                copyButton.setStyle("-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; " +
                        "-fx-text-fill: blue; " +
                        "-fx-underline: true;");
                copyButton.setCursor(Cursor.DEFAULT); // 恢复默认光标
            });

            copyButton.setOnAction(event -> copyToClipboard(content));

            // 填充器使按钮靠右
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            hbox.getChildren().addAll(textLabel, spacer, copyButton);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                content = item;
                textLabel.setText(item);
                setText(null);
                setGraphic(hbox);
            }
        }

        private void copyToClipboard(String text) {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(text);
            clipboard.setContent(content);
        }
    }

    private void showEditForm() {
        // 创建表单界面
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(5);

        TextField titleField = new TextField(viewModel.getSelectedTableRowViewModel().getTitle());
        TextField contentField = new TextField(viewModel.getSelectedTableRowViewModel().getContent());

        HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setHtmlText("nothing");
        form.addRow(1, new Label("Title:"), titleField);
        form.addRow(2, new Label("Content:"), contentField);
//        form.addRow(3, htmlEditor);
        form.add(htmlEditor, 0, 3, 2, 1);

        // 创建对话框
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("Edit");

        // 创建保存按钮
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {

            viewModel.getSelectedTableRowViewModel().getBusiness().setTitle(titleField.getText());
            viewModel.getSelectedTableRowViewModel().getBusiness().setContent(contentField.getText());
//            viewModel.update(titleField.getText(), contentField.getText());
            businessTable.refresh();
            dialogStage.close();
        });

        // 创建取消按钮
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> dialogStage.close());

        HBox buttonBox = new HBox(10, saveButton, cancelButton);
        buttonBox.setPadding(new Insets(10));

        VBox dialogVBox = new VBox(10, form, buttonBox);
        dialogVBox.setPadding(new Insets(20));

        Scene dialogScene = new Scene(dialogVBox);
        dialogStage.setScene(dialogScene);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.showAndWait();
    }
}
