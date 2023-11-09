package com.example.toolscansystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.*;

public class SearchScreen {

    @FXML
    private TextField searchTextBox;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label outputLabel;

    public void onSearchButtonClick(ActionEvent actionEvent) {

        if (searchTextBox.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Data Missing");
            alert.setContentText("Please enter both a Tool and a Store");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Searching");
            alert.setHeaderText("Success");
            alert.setContentText("Tool: " + searchTextBox.getText() + ".");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });

            //variables
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            } catch (ClassNotFoundException cnfex) {
                System.out.println("Problem in loading or " + "registering MS Access JDBC driver");
                cnfex.printStackTrace();
            }
            try {
                String msAccDB = "./BookingDatabase.accdb";
                String dbURL = "jdbc:ucanaccess://" + msAccDB;

                connection = DriverManager.getConnection(dbURL);

                statement = connection.createStatement();

                String query = "SELECT * FROM BookingReservations WHERE id = " + searchTextBox.getText();
                System.out.println(query);

                resultSet = statement.executeQuery(query);
                System.out.println("SELECT Statement run.");

                resultSet.next();
                outputLabel.setText(resultSet.getString(3));

                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "\t" +
                            resultSet.getString(2) + "\t" +
                            resultSet.getString(3) + "\t" +
                            resultSet.getString(4) + "\t" +
                            resultSet.getString(5) + "\t" +
                            resultSet.getString(6) + "\t" +
                            resultSet.getString(7) + "\t" +
                            resultSet.getString(8));
                }

            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            } finally {
                try {
                    if (null != connection) {
                        resultSet.close();
                        statement.close();

                        connection.close();
                    }
                } catch (SQLException sqlex) {
                    sqlex.printStackTrace();
                }

            }
        }


    }

    public void onHomeButtonClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
