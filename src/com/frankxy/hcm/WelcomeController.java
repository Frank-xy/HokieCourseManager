package com.frankxy.hcm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane welcomepane;
    @FXML
    private ImageView welcomebackground;
    @FXML
    private Pane dragpane;
    @FXML
    private JFXButton exitbutton;
    @FXML
    private JFXTextField usernamefield;
    @FXML
    private JFXPasswordField passwordfield;

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
