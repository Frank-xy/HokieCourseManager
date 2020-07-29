package com.frankxy.hcm;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WelcomeController {
    @FXML
    private Pane welcomepane;
    @FXML
    private JFXButton exitbutton;

    private void exitConfirm() {
        Stage stage = (Stage) exitbutton.getScene().getWindow();
        stage.close();
    }
}
