package org.example.database;

import java.sql.*;

public class DatabaseConnection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.hsql.jdbcDriver");
        String url = "jdbc:hsqldb:mem:test";

        Connection connection = DriverManager.getConnection(url);
    }

    private boolean registerUser(String username, String password, Connection connection) throws SQLException {
        // Checando se usuário já existe no banco de dados
        String checkUser = "SELECT USERNAME FROM TB_USERS WHERE USERNAME = ?";
        PreparedStatement statement = connection.prepareStatement(checkUser);
        statement.setString(1, username);

        ResultSet rs = statement.executeQuery(checkUser);
        rs.next();
        String result = rs.getString("USERNAME");
        statement.close();

        // Registrando usuário no banco de dados
        if (result.isEmpty()) {
            statement.executeUpdate();
            statement = connection.prepareStatement("INSERT INTO TB_USERS (USERNAME, PASSWORD) VALUES (?, ?)");
            return true;
        }
        return false;
    }

    private boolean login(String username, String password, Connection connection) throws SQLException {
        String checkUser = "SELECT * FROM TB_USERS WHERE USERNAME = ?";
        PreparedStatement statement = connection.prepareStatement(checkUser);
        statement.setString(1, username);

        ResultSet rs = statement.executeQuery();
        rs.next();
        String usr = rs.getString("USERNAME");
        String pwd = rs.getString("PASSWORD");

        if (usr.equals(username) && pwd.equals(password))
            return true;
        return false;
    }
}