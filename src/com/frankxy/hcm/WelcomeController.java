package com.frankxy.hcm;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WelcomeController {
    /**
     * The original X position
     */
    private double xOffset = 0;
    /**
     * The original Y position
     */
    private double yOffset = 0;
    @FXML
    private Pane welcomepane;
    @FXML
    private Pane dragpane;
    @FXML
    private JFXButton exitbutton;

    /**
     * exitConfirm
     */
    public void exitConfirm() {
        Stage stage = (Stage) exitbutton.getScene().getWindow();
        stage.close();
    }

    /**
     * dragStage
     */
    public void dragStage() {
        Stage stage = (Stage) dragpane.getScene().getWindow();
        dragpane.setOnMousePressed(mouseEvent -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        stage.getScene().setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - xOffset);
            stage.setY(mouseEvent.getScreenY() - yOffset);
        });
    }
}
