package com.wpa.page;

import com.wpa.service.BusinessService;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
        Accordion accordion = new Accordion();


        var ta = new TextArea("this is good news");
        ta.setWrapText(true);
        ta.setEditable(false);
        TitledPane pane1 = new TitledPane("Boats" , ta);


        TitledPane pane2 = new TitledPane("Cars"  , new Label("Show all cars available"));
        TitledPane pane3 = new TitledPane("Planes", new Label("Show all planes available"));

        accordion.getPanes().add(pane1);
        accordion.getPanes().add(pane2);
        accordion.getPanes().add(pane3);
        accordion.setPadding(new Insets(10, 10, 10, 10));

        TableView tableView = new TableView();

        TableColumn<Person, String> column1 =
                new TableColumn<>("First Name");

        column1.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));


        TableColumn<Person, String> column2 =
                new TableColumn<>("Last Name");

        column2.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));


        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        tableView.getItems().add(
                new Person("John", "Doe"));
        tableView.getItems().add(
                new Person("Jane", "Deer"));
        tableView.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);

        VBox vbox = new VBox(tableView);
        vbox.setPadding(new Insets(10, 10, 10, 10));
//        return vbox;

        return accordion;
        //return accordion;
    }



}
