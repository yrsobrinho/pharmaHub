package org.example.database;

import org.example.entities.Product;

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
            statement = connection.prepareStatement("INSERT INTO TB_USERS (USERNAME, PASSWORD) VALUES (?, ?)");
            statement.executeUpdate();
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

    private boolean insertProduct(Product product, Connection connection) throws SQLException {
        String sql = "INSERT INTO TB_PRODUCTS(NAME, DESCRIPTION, PRICE) VALUES (?, ?, ?)";
        String checkIfExists = "SELECT * FROM TB_PRODUCTS WHERE NAME = ?";

        PreparedStatement checkProduct = connection.prepareStatement(checkIfExists);
        PreparedStatement statement = connection.prepareStatement(sql);

        checkProduct.setString(1, product.getName());
        ResultSet resultSet = checkProduct.executeQuery();
        resultSet.next();

        if (!resultSet.getString("NAME").isEmpty())
            return false;

        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());
        statement.setDouble(3, product.getPrice());
        int affectedRows = statement.executeUpdate();
        statement.close();

        if (affectedRows != 0)
            return true;
        return false;
    }
}