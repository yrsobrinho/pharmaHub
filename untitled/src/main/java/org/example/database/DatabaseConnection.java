package org.example.database;

import org.example.entities.Category;
import org.example.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        statement.close();
        String usr = rs.getString("USERNAME");
        String pwd = rs.getString("PASSWORD");

        return usr.equals(username) && pwd.equals(password);
    }

    private boolean insertProduct(Product product, Connection connection) throws SQLException {
        String sql = "INSERT INTO TB_PRODUCTS(NAME, DESCRIPTION, PRICE) VALUES (?, ?, ?)";
        String checkIfExists = "SELECT * FROM TB_PRODUCTS WHERE NAME = ?";

        PreparedStatement checkProduct = connection.prepareStatement(checkIfExists);
        PreparedStatement statement = connection.prepareStatement(sql);

        checkProduct.setString(1, product.getName());
        ResultSet resultSet = checkProduct.executeQuery();
        resultSet.next();
        checkProduct.close();

        if (!resultSet.getString("NAME").isEmpty())
            return false;

        statement.setString(1, product.getName());
        statement.setObject(2, product.getCategory());
        statement.setDouble(3, product.getPrice());
        int affectedRows = statement.executeUpdate();
        statement.close();

        return affectedRows != 0;
    }

    private List<Product> searchByProductName(String name, Connection connection) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM TB_PRODUCTS WHERE NAME LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Long id = rs.getLong("ID");
            String productName = rs.getString("NAME");
            products.add(new Product(rs.getLong("ID"), rs.getString("NAME"), (Category) rs.getObject("CATEGORY"), rs.getDouble("PRICE")));
        }
        statement.close();
        return products;
    }

    private Product searchByProductId(Long id, Connection connection) throws SQLException {
        String sql = "SELECT * FROM TB_PRODUCTS WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            Long productId = rs.getLong("ID");
            String productName = rs.getString("NAME");
            Object productCategory = rs.getObject("CATEGORY");
            Double productPrice = rs.getDouble("PRICE");
            statement.close();
            return new Product(productId, productName, (Category) productCategory, productPrice);
        }
        statement.close();
        return null;
    }

    private Product searchByManufacturerId(Long id, Connection connection) throws SQLException {
        String sql = "SELECT * FROM TB_PRODUCTS WHERE MANUFACTURER_ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            Long productId = rs.getLong("ID");
            String productName = rs.getString("NAME");
            Object productCategory = rs.getString("CATEGORY");
            Double productPrice = rs.getDouble("PRICE");
            statement.close();
            return new Product(productId, productName, (Category) productCategory, productPrice);
        }
        statement.close();
        return null;
    }

    private boolean deleteProductById(Long id, Connection connection) throws SQLException {
        String sql = "DELETE FROM TB_PRODUCTS WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        int affectedRows = statement.executeUpdate();
        statement.close();
        return affectedRows != 0;
    }
}