package com.wpa.view.main;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SplitPane;

public class MainView implements FxmlView<MainViewModel> {
    @InjectViewModel
    private MainViewModel viewModel;

    @FXML
    private SplitPane splitPane;

    public void initialize() {
        // Initialize the view model here.
        System.out.println("Initializing MainView");
    }

    public SplitPane getSplitPane() {
        return splitPane;
    }
}
