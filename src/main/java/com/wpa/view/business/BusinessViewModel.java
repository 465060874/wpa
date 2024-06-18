package com.wpa.view.business;

import com.wpa.model.business.Business;
import com.wpa.service.BusinessService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class BusinessViewModel implements ViewModel {

    private BusinessService businessService;

    private final ObservableList<BusinessTableViewModel> businessList = FXCollections.observableArrayList();

    private final ReadOnlyObjectWrapper<Business> selectedContact = new ReadOnlyObjectWrapper<>();

    private final ObjectProperty<BusinessTableViewModel> selectedTableRow = new SimpleObjectProperty<>();

    private String type;

    public BusinessViewModel(){

    }
    public BusinessViewModel(String type) {
        this.type = type;
    }


    public void initialize() {
        updateBusinessList();
    }

    private void updateBusinessList() {
    }


}
