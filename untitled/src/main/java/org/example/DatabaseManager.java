package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsql.jdbcDriver");
        String url = "jdbc:HypersonicSQL:mem:mymemdb";
        return DriverManager.getConnection(url, "SA", "");
    }

    public static boolean register(String username, String password) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
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
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            return true;
        }
        return false;
    }

    public static boolean login(String username, String password) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
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

    public static boolean insertProduct(Product product) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
        String sql = "INSERT INTO TB_PRODUCTS(NAME, MANUFACTURER_ID, PRICE) VALUES (?, ?, ?)";
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
        statement.setInt(2, product.getManufacturer().getId());
        statement.setDouble(3, product.getPrice());
        int affectedRows = statement.executeUpdate();
        statement.close();

        return affectedRows != 0;
    }

    public static List<Product> searchByProductName(String name) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM TB_PRODUCTS WHERE NAME LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            String productName = rs.getString("NAME");
            Double price = rs.getDouble("PRICE");
            Manufacturer m = DatabaseManager.searchManufacturerById(rs.getInt("MANUFACTURER_ID"));
            if (m != null)
                products.add(new Product(id, productName, price, m));
        }
        statement.close();
        return products;
    }

    public static Manufacturer searchManufacturerById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
        String manufacturer = "SELECT * FROM TB_MANUFACTURERS WHERE ID = ?";
        PreparedStatement manufacturerStatement = connection.prepareStatement(manufacturer);
        manufacturerStatement.setInt(1, id);
        ResultSet rsManufacturer = manufacturerStatement.executeQuery();
        if (rsManufacturer.next())
            return new Manufacturer(rsManufacturer.getInt("ID"), rsManufacturer.getString("NAME"));
        return null;
    }

    public static Product searchByProductId(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
        String sql = "SELECT * FROM TB_PRODUCTS WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            int productId = rs.getInt("ID");
            String productName = rs.getString("NAME");
            Double productPrice = rs.getDouble("PRICE");
            Manufacturer m = DatabaseManager.searchManufacturerById(rs.getInt("MANUFACTURER_ID"));
            statement.close();
            return new Product(productId, productName, productPrice, m);
        }
        statement.close();
        return null;
    }

    public static List<Product> searchProductsByManufacturerId(int id) throws SQLException, ClassNotFoundException {
        List<Product> products = new ArrayList<>();
        Connection connection = DatabaseManager.getConnection();
        String sql = "SELECT * FROM TB_PRODUCTS WHERE MANUFACTURER_ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            Manufacturer m = DatabaseManager.searchManufacturerById(rs.getInt("MANUFACTURER_ID"));
            int productId = rs.getInt("ID");
            String productName = rs.getString("NAME");
            Double productPrice = rs.getDouble("PRICE");
            products.add(new Product(productId, productName,productPrice, m));
        }
        statement.close();
        return products;
    }

    public static boolean deleteProductById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
        String sql = "DELETE FROM TB_PRODUCTS WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        int affectedRows = statement.executeUpdate();
        statement.close();
        return affectedRows != 0;
    }

    public static void createUserTable() throws SQLException, ClassNotFoundException {
        String userTableQuery = "CREATE TABLE TB_USERS (id INT PRIMARY KEY, username VARCHAR(255), password VARCHAR(255))";
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(userTableQuery);
        statement.close();
    };

    public static void createManufacturerTable() throws SQLException, ClassNotFoundException {
        String manufacturerTableQuery = "CREATE TABLE TB_MANUFACTURERS (id INT PRIMARY KEY, name VARCHAR(255))";
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(manufacturerTableQuery);
        statement.close();
    };

    public static void createProductTable() throws SQLException, ClassNotFoundException {
        String productTableQuery = "CREATE TABLE TB_PRODUCTS (id INT PRIMARY KEY, name VARCHAR(255), price DOUBLE PRECISION, manufacturer_id INT, FOREIGN KEY (manufacturer_id) REFERENCES TB_MANUFACTURERS(id))";
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(productTableQuery);
        statement.close();
    };
}