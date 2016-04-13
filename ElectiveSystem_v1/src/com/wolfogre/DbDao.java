package com.wolfogre;

import java.sql.*;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/8.
 */
public class DbDao {
    private Connection connection;
    private String driver;
    private String url;
    private String username;
    private String password;

    public DbDao() {
    }

    public DbDao(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        if(connection == null){
            Class.forName(this.driver);
            connection = DriverManager.getConnection(url, username, this.password);
        }
        return connection;
    }

    public boolean insert(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        int index = 1;
        for(Object object: args)
            preparedStatement.setObject(index++, object);
        return preparedStatement.executeUpdate() == 1;
    }

    public ResultSet query(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        int index = 1;
        for(Object object: args)
            preparedStatement.setObject(index++, object);
        return preparedStatement.executeQuery();
    }

    public void modify(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        int index = 1;
        for(Object object: args)
            preparedStatement.setObject(index++, object);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void closeConnection() throws SQLException {
        if(connection != null && !connection.isClosed())
            connection.close();
    }
}
