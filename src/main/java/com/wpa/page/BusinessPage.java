package com.wpa.page;

import com.dlsc.gemsfx.ExpandingTextArea;
import com.dlsc.gemsfx.Spacer;
import com.wpa.service.BusinessService;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;

@Component
@Scope("prototype")
public class BusinessPage extends AbstractPage {

    @Autowired
    private BusinessService businessService;

    public BusinessPage() {
        super();
    }


    @Override
    Node buildContentNode() {
        VBox vBox = new VBox(1);
        Button btnAdd = new Button("新增");

        vBox.getChildren().add(btnAdd);
        Spacer spacer1 = new Spacer();
        spacer1.setStyle("-fx-background-color: rgba(255,192,203,0.3);");
        vBox.getChildren().add(spacer1);
        Accordion accordion = new Accordion();

        TitledPane pane1 = new TitledPane("Boats" , createContentBox());
        TitledPane pane2 = new TitledPane("Cars"  , createContentBox());
        TitledPane pane3 = new TitledPane("Planes", createContentBox());

        accordion.getPanes().add(pane1);
        accordion.getPanes().add(pane2);
        accordion.getPanes().add(pane3);
        vBox.getChildren().add(accordion);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        btnAdd.setOnAction(event -> {
            TitledPane pane = new TitledPane("New" , createContentBox());
            accordion.getPanes().add(pane);
        });

//        TableView tableView = new TableView();
//
//        TableColumn<Person, String> column1 =
//                new TableColumn<>("First Name");
//
//        column1.setCellValueFactory(
//                new PropertyValueFactory<>("firstName"));
//
//
//        TableColumn<Person, String> column2 =
//                new TableColumn<>("Last Name");
//
//        column2.setCellValueFactory(
//                new PropertyValueFactory<>("lastName"));
//
//
//        tableView.getColumns().add(column1);
//        tableView.getColumns().add(column2);
//
//        tableView.getItems().add(
//                new Person("John", "Doe"));
//        tableView.getItems().add(
//                new Person("Jane", "Deer"));
//        tableView.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
//
//        VBox vbox = new VBox(tableView);
//        vbox.setPadding(new Insets(10, 10, 10, 10));
//        return vbox;

        return vBox;
        //return accordion;
    }

    private HBox createContentBox(){
        // 创建TextArea
        ExpandingTextArea textArea = new ExpandingTextArea();

        textArea.setEditable(false);
        textArea.setPrefRowCount(3); // 设置文本区域的首选行数
        // 创建Button
        Button button = new Button("编辑");
        button.setOnAction(event -> {
            if (textArea.isEditable()){
                textArea.setEditable(false);
                button.setText("编辑");
            }else {
                textArea.setEditable(true);
                button.setText("保存");
            }
            //
        });

        // 创建HBox布局容器
        HBox hbox = new HBox(10); // 10是控件之间的间距
        hbox.getChildren().addAll(textArea, button);

        // 设置TextArea占据更多空间
        HBox.setHgrow(textArea, Priority.ALWAYS);
        return hbox;
    }



}
