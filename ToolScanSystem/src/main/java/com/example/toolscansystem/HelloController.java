package com.example.toolscansystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField unit;

    @FXML
    private TextField tool;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(tool.getText() + " has been moved to " + unit.getText());
    }
}