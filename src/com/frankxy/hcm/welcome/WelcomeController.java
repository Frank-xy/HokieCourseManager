package com.frankxy.hcm.welcome;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WelcomeController{
    /**
     * The original X and Y position
     */
    private double xOffset, yOffset = 0;
    /**
     * The scroll cursor state
     */
    private String scrollState = "Middle";
    private boolean gaussian = false;
    @FXML
    private AnchorPane welcomepane;
    @FXML
    private Pane dragpane;
    @FXML
    private JFXButton exitbutton;
    @FXML
    private JFXTextField signinUsername;
    @FXML
    private JFXTextField loginUsername;
    @FXML
    private JFXPasswordField signinPassword;
    @FXML
    private JFXPasswordField loginPassword;
    @FXML
    private AnchorPane loginpane;
    @FXML
    private AnchorPane signinpane;
    @FXML
    private ImageView welcomebackground;

    public void initialize() {
        welcomepane.setOnScroll(scrollEvent -> {
            unGaussianBlur();
            if (scrollEvent.getDeltaY() < 0) {
                if (scrollState.equals("Left")) {
                    scrollAnimation(signinpane, 686);
                    scrollState = "Middle";
                } else if (scrollState.equals("Middle")) {
                    scrollAnimation(loginpane, 350);
                    scrollState = "Right";
                }
            } else {
                if (scrollState.equals("Right")) {
                    scrollAnimation(loginpane, 14);
                    scrollState = "Middle";
                } else if (scrollState.equals("Middle")) {
                    scrollAnimation(signinpane, 350);
                    scrollState = "Left";
                }
            }
        });
    }
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

    /**
     * loginPaneAnimation
     */
    public void scrollAnimation(AnchorPane target, int endValue) {
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(target.layoutXProperty(), endValue);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(350), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void gaussianBlur() {
        if (!gaussian) {
            GaussianBlur gaussianBlur = new GaussianBlur(0);
            welcomebackground.setEffect(gaussianBlur);
            for (Node children : loginpane.getChildren()) {
                if (!children.getId().equals(loginPassword.getId())) {
                    children.setEffect(gaussianBlur);
                }
            }
            for (Node children : signinpane.getChildren()) {
                if (!children.getId().equals(signinPassword.getId())) {
                    children.setEffect(gaussianBlur);
                }
            }
            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(gaussianBlur.radiusProperty(), 10);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(350), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();

            gaussian = true;
        }
    }

    public void unGaussianBlur() {
        if (gaussian) {
            GaussianBlur gaussianBlur = new GaussianBlur(10);
            welcomebackground.setEffect(gaussianBlur);
            for (Node children : loginpane.getChildren()) {
                if (!children.getId().equals(loginPassword.getId())) {
                    children.setEffect(gaussianBlur);
                }
            }
            for (Node children : signinpane.getChildren()) {
                if (!children.getId().equals(signinPassword.getId())) {
                    children.setEffect(gaussianBlur);
                }
            }
            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(gaussianBlur.radiusProperty(), 0);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(350), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            gaussian = false;
        }
    }
}
