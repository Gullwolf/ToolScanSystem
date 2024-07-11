package com.example.toolscansystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.*;

public class MoveTool {

    @FXML
    private TextField toolTextBox;

    @FXML
    private TextField storeTextBox;

    @FXML
    private AnchorPane rootPane;

    public void onSubmitButtonClick() throws SQLException {

        if (toolTextBox.getText().isBlank() || storeTextBox.getText().isBlank()) {
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
            alert.setTitle("Confirmation");
            alert.setHeaderText("Success");
            alert.setContentText("Tool: " + toolTextBox.getText() + ". Moved successfully to Store: " + storeTextBox.getText() + ".");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });

            //variables
            int check = -1;
            Connection connection = null;
            Statement statement = null;
//            ResultSet resultSet = null;
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

                String query = "UPDATE ToolStore SET Store = '" + storeTextBox.getText() + "' WHERE Tool = '" + toolTextBox.getText() + "';";
                System.out.println(query);

                check = statement.executeUpdate(query);
                System.out.println(check);
                System.out.println("UPDATE Statement run.");

            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            } finally {
                try {
                    if (check == 0) {
                        Alert alertEnterNew = new Alert(Alert.AlertType.CONFIRMATION);
                        alertEnterNew.setTitle("ERROR");
                        alertEnterNew.setHeaderText("Tool not found");
                        alertEnterNew.setContentText("Do you want to add tool: " + toolTextBox.getText() + " to the database?");
                        alertEnterNew.showAndWait().ifPresent(rs -> {
                            if (rs == ButtonType.OK) {
                                insertInToTable(toolTextBox.getText(), storeTextBox.getText());
                            }
                        });
                    } else if (null != connection) {
//                        resultSet.close();
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

    private void insertInToTable(String tool, String store) {
        //variables
        Connection connection = null;
        Statement statement = null;
//            ResultSet resultSet = null;
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

            String query = "INSERT INTO ToolStore (Tool, Store) VALUES ('" + tool + "', '" + store + "');";
            System.out.println(query);

            statement.execute(query);
            System.out.println("INSERT Statement run.");

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
