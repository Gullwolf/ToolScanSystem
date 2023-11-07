package com.example.toolscansystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainScreen {
    @FXML
    private AnchorPane rootPane;

    public void onMoveToolButtonOnClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("moveTool.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
