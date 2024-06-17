package com.wpa.view.business;

import com.wpa.dto.Criteria;
import com.wpa.view.main.MainViewModel;
import de.saxsys.mvvmfx.Context;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectContext;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class BusinessView implements FxmlView<BusinessViewModel> {
    @FXML
    private TableView<BusinessTableViewModel> businessTable;
    private String category;

    private Criteria criteria;

    @InjectContext
    private Context context;

    @InjectViewModel
    private BusinessViewModel viewModel;

    public void initialize() {
        System.out.println("BusinessView initialized="+category);
//        businessTable.setItems(viewModel.getContactList());

//        viewModel.selectedTableRowProperty().bind(contactTable.getSelectionModel().selectedItemProperty());
//
//        // When the selectedTableRowProperty changes in the viewModel we need to update the table
//        viewModel.setOnSelect(vm -> contactTable.getSelectionModel().select(vm));
    }

    public void setCategory(String category){
        this.category = category;
    }
}