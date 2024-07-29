package com.example.toolscansystem;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainScreenController {
    @FXML
    private AnchorPane rootPane;

    public void onMoveToolButtonClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("moveToolScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void onCloseButtonClick(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void onBarcodeGenerateButtonClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("barcodeGeneratorScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void onSearchButtonClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("searchScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void onViewTableButtonClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("tableViewScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void onNukeButtonClick(ActionEvent actionEvent) throws  IOException {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException cnfex) {
            System.out.println("Problem in loading or " + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
        try {
            String msAccDB = "./ToolStore.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB;

            connection = DriverManager.getConnection(dbURL);

            statement = connection.createStatement();

            String query = "DELETE FROM ToolStore;";
            System.out.println(query);

            statement.execute(query);
            System.out.println("Delete Statement run.");

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } finally {
            try {
                if (null != connection) {
//                    resultSet.close();
                    statement.close();
                    connection.close();
                }
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }

}



