package com.example.toolscansystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.*;

public class tableViewScreenController {

    @FXML
    TableView<TS> toolStoreTable;

    @FXML
    TableColumn<TS, String> toolColumn;

    @FXML
    TableColumn<TS, String> storeColumn;

    @FXML
    private AnchorPane rootPane;

    ObservableList<TS> observableTools;


    public void initialize() {
        // Initialize the ObservableList, Set up the columns
        observableTools = FXCollections.observableArrayList();
        toolColumn.setCellValueFactory(new PropertyValueFactory<>("tool"));
        storeColumn.setCellValueFactory(new PropertyValueFactory<>("store"));

        // Load data into the table
        loadTableData();
    }

    public void loadTableData() {
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
            String msAccDB = "./ToolStore.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB;

            connection = DriverManager.getConnection(dbURL);
            statement = connection.createStatement();
            String query = "SELECT * FROM ToolStore;";
            System.out.println(query);
            resultSet = statement.executeQuery(query);
            System.out.println("SELECT Statement run.");

            while (resultSet.next()) {
                String tool = resultSet.getString("Tool");
                String store = resultSet.getString("Store");
                TS ts = new TS(tool, store);
                System.out.println(tool);
                System.out.println(store);
                observableTools.add(ts);
            }

            toolStoreTable.setItems(observableTools);

            //toolStoreTable.setItems((ObservableList<TS>) resultSet);

            // only if necessary
            //observableCustomers.clear();
            // add customers
            //observableCustomers.addAll(resultSet);
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }

    public void onHomeButtonClick(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
