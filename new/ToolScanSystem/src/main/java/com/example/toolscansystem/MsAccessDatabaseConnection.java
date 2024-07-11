package com.example.toolscansystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MsAccessDatabaseConnection {

    public static void main(String[] args){
        //variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //Loading or registering Oracle JDBC driver class
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }catch (ClassNotFoundException cnfex){
            System.out.println("Problem in loading or " + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }

        //Opening database connection
        try{
            String msAccDB = "./ToolStore.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB;

            connection = DriverManager.getConnection(dbURL);

            statement =connection.createStatement();

            resultSet = statement.executeQuery("SELECT * ToolStore");

            System.out.println("ID\tTitle\tFirstName\tLastName\tCustomerMessage\tPhoneNumber\tBookingDate\tNumberOfPeople");
            System.out.println("==\t=====\t=========\t========\t===============\t===========\t===========\t==============");
            //Processing Data Returned.
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1) + "\t" +
                        resultSet.getString(2) + "\t" +
                        resultSet.getString(3) + "\t" +
                        resultSet.getString(4) + "\t" +
                        resultSet.getString(5) + "\t" +
                        resultSet.getString(6) + "\t" +
                        resultSet.getString(7) + "\t" +
                        resultSet.getString(8));
            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }finally{
            try{
                if(null != connection){
                    resultSet.close();
                    statement.close();

                    connection.close();
                }
            }catch (SQLException sqlex){
                sqlex.printStackTrace();
            }
        }
    }

}
