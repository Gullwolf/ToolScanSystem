package com.example.toolscansystem;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainScreen {
    @FXML
    private AnchorPane rootPane;

    public void onMoveToolButtonClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("moveTool.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void onCloseButtonClick(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void onBarcodeGenerateButtonClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("barcodeGenerator.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void onSearchButtonClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("searchScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}



