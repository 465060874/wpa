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

import java.util.ArrayList;
import java.util.List;

public class BusinessViewModel implements ViewModel {

    private BusinessService businessService;

    private final ObservableList<BusinessTableViewModel> businessList = FXCollections.observableArrayList();

    private final SimpleObjectProperty<Business> selectedBusiness = new SimpleObjectProperty<>();

    private final ObjectProperty<BusinessTableViewModel> selectedTableRow = new SimpleObjectProperty<>();

    private BusinessTableViewModel selectedTableRowViewModel = new BusinessTableViewModel();

    private String type;

    private List<Business> allBusinessList = new ArrayList<>();

    public BusinessViewModel(){

    }
    public BusinessViewModel(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void initialize() {
        allBusinessList.add(new Business("online BR","flow"));
        allBusinessList.add(new Business("EDI BR","integration"));
        allBusinessList.forEach(contact -> businessList.add(new BusinessTableViewModel(contact)));
    }

    public ObservableList<BusinessTableViewModel> getBusinessList() {
        return businessList;
    }

    public ObjectProperty<BusinessTableViewModel> selectedTableRowProperty() {
        return selectedTableRow;
    }

    public void setSelectedTableRowViewModel(BusinessTableViewModel selectedTableRowViewModel) {
        this.selectedTableRowViewModel = selectedTableRowViewModel;
    }

    public BusinessTableViewModel getSelectedTableRowViewModel() {
        return selectedTableRowViewModel;
    }


    public void update(String title, String content){
        for(BusinessTableViewModel model: businessList) {
            model.getBusiness().setTitle(title);
            model.getBusiness().setContent(content);
        }
        System.out.println("done");
    }

}
