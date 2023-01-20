package org.example.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JDBCPostgreSQL {
    private final String url;
    private String username;
    private String password;
    private Connection connection;


    JDBCPostgreSQL(String username,String password) throws SQLException {
        url = "jdbc:postgresql://localhost/test";
        this.username = username;
        this.password = password;
    }
    public Connection connect() throws SQLException {
        connection = null;
        try{
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connection succesfull!");
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if(connection != null) {
            connection.close();
        }
    }

    public Map<Integer, String> allData() throws SQLException {
        Map<Integer, String> result = new HashMap<Integer, String>();
        String query = "SELECT * FROM streets";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String streetName = resultSet.getString("street_name");
            result.put(id,streetName);
        }
        return result;
    }

    public String selectStreet(int index) throws SQLException {
        String query = "SELECT * FROM streets WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,index);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        return resultSet.getString("street_name");
    }

}
