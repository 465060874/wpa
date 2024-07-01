package com.wpa.page;

import com.dlsc.gemsfx.ExpandingTextArea;
import com.dlsc.gemsfx.Spacer;
import com.wpa.entity.BusinessKnowledge;
import com.wpa.service.BusinessKnowledgeService;
import com.wpa.service.BusinessService;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class BusinessPage extends AbstractPage {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private BusinessKnowledgeService businessKnowledgeService;

    Accordion accordion = new Accordion();

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

//
//        TitledPane pane1 = new TitledPane("Boats" , createContentBox());
//        TitledPane pane2 = new TitledPane("Cars"  , createContentBox());
//        TitledPane pane3 = new TitledPane("Planes", createContentBox());
        List<BusinessKnowledge> businessKnowledgeList;
        try {
            businessKnowledgeList = businessKnowledgeService.findByType("BR");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (BusinessKnowledge businessKnowledge : businessKnowledgeList) {
            TitledPane pane = new TitledPane(businessKnowledge.getTitle(), createContentBox(businessKnowledge));
            accordion.getPanes().add(pane);
        }
//        accordion.getPanes().add(pane1);
//        accordion.getPanes().add(pane2);
//        accordion.getPanes().add(pane3);
        vBox.getChildren().add(accordion);

        vBox.setPadding(new Insets(10, 10, 10, 10));
        btnAdd.setOnAction(event -> {
            showEditDialog(BusinessKnowledge.getInstance("BR"));

        });



        return vBox;
        //return accordion;
    }

    private HBox createContentBox(BusinessKnowledge businessKnowledge){
        // 创建TextArea
        ExpandingTextArea textArea = new ExpandingTextArea();

        textArea.setEditable(false);
        textArea.appendText(businessKnowledge.getContent());
        textArea.setPrefRowCount(3); // 设置文本区域的首选行数
        // 创建Button
        Button button = new Button("编辑");
        button.setUserData(businessKnowledge);
        button.setOnAction(event -> {
            if (textArea.isEditable()){
                textArea.setEditable(false);
                BusinessKnowledge businessKnowledge1 = (BusinessKnowledge) button.getUserData();
                      businessKnowledge1.setContent(textArea.getText());
                      businessKnowledgeService.update(businessKnowledge1);
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

    private void showEditDialog(BusinessKnowledge businessKnowledge) {
        // 创建编辑对话框
        Dialog<BusinessKnowledge> dialog = new Dialog<>();
        dialog.setTitle("添加业务信息");

        // 设置按钮
        ButtonType okButtonType = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        VBox vbox = new VBox();
        vbox.getChildren().add(new Label("Title"));

        // 创建表单
        TextField titleField = new TextField(businessKnowledge.getTitle());
        vbox.getChildren().add(titleField);
        vbox.getChildren().add(new Label("Short Description"));
        TextField shortDescField = new TextField(businessKnowledge.getShortDescription());
        vbox.getChildren().add(shortDescField);
        vbox.getChildren().add(new Label("Content"));
        TextArea contentArea = new TextArea(businessKnowledge.getContent());
        vbox.getChildren().add(contentArea);

        dialog.getDialogPane().setContent(vbox);

        // 结果转换
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return new BusinessKnowledge(titleField.getText(), shortDescField.getText(), contentArea.getText());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            businessKnowledge.setTitle(result.getTitle());
            businessKnowledge.setShortDescription(result.getShortDescription());
            businessKnowledge.setContent(result.getContent());
            BusinessKnowledge dbData = businessKnowledgeService.update(businessKnowledge);
            if(dbData!=null){
                accordion.getPanes().add(new TitledPane(dbData.getTitle(), createContentBox(dbData)));
            }
        });
    }

}
