import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Classe que realiza todas as operações com o banco de dados
public class DatabaseManager {

    // Cria conexão com banco de dados, carregando driver
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsql.jdbcDriver");
        String url = "jdbc:HypersonicSQL:localhost";
        return DriverManager.getConnection(url, "sa", "");
    }

    // Registra usuários no banco de dados
    public static boolean register(String username, String password) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
        // Checando se usuário já existe no banco de dados
        String checkUser = "SELECT USERNAME FROM TB_USERS WHERE USERNAME = ?";
        PreparedStatement statement = connection.prepareStatement(checkUser);
        statement.setString(1, username);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            statement.close();
            connection.close();
            return false;
        }
        else {
            // Registrando usuário no banco de dados
            statement = connection.prepareStatement("INSERT INTO TB_USERS (USERNAME, PASSWORD) VALUES (?, ?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            connection.close();
            return true;
        }
    }

    // Autentica usuários, checando se existem e se a senha inserida coincide com a senha armazenada
    public static boolean login(String username, String password) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
        String checkUser = "SELECT * FROM TB_USERS WHERE USERNAME = ?";
        PreparedStatement statement = connection.prepareStatement(checkUser);
        statement.setString(1, username);

        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            String usr = rs.getString("USERNAME");
            String pwd = rs.getString("PASSWORD");
            statement.close();
            connection.close();
            return usr.equals(username) && pwd.equals(password);
        }
        statement.close();
        connection.close();
        return false;
    }

    // Registra produto no banco de dados, checando se não há um registro com o mesmo nome
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

        try {
            String nameCheck = resultSet.getString("NAME");
            if (nameCheck != null) {
                connection.close();
                return false;
            }
        } catch (Exception e) {}
        statement.setString(1, product.getName());
        statement.setInt(2, product.getManufacturer().getId());
        statement.setDouble(3, product.getPrice());
        int affectedRows = statement.executeUpdate();
        statement.close();
        connection.close();
        return affectedRows != 0;
    }

    // Busca produto por nome
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
        connection.close();
        return products;
    }

    // Busca fabricante por ID
    public static Manufacturer searchManufacturerById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
        String manufacturer = "SELECT * FROM TB_MANUFACTURERS WHERE ID = ?";
        PreparedStatement manufacturerStatement = connection.prepareStatement(manufacturer);
        manufacturerStatement.setInt(1, id);
        ResultSet rsManufacturer = manufacturerStatement.executeQuery();
        if (rsManufacturer.next()) {
            connection.close();
            return new Manufacturer(rsManufacturer.getInt("ID"), rsManufacturer.getString("NAME"));
        }
        connection.close();
        return null;
    }

    // Busca produtos pelo nome do fabricante
    public static List<Product> searchProductsByManufacturerName(String name) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
        String manufacturerQuery = "SELECT * FROM TB_MANUFACTURERS WHERE NAME LIKE ?";
        PreparedStatement manufacturerStatement = connection.prepareStatement(manufacturerQuery);
        manufacturerStatement.setString(1, name);
        ResultSet rsManufacturer = manufacturerStatement.executeQuery();
        Manufacturer m;
        if (rsManufacturer.next()) {
            int id = rsManufacturer.getInt("ID");
            String manufacturerName = rsManufacturer.getString("NAME");
            m = new Manufacturer(id, manufacturerName);
            manufacturerStatement.close();
        }
        else {
            connection.close();
            return null;
        }
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM TB_PRODUCTS WHERE NAME LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            String productName = rs.getString("NAME");
            Double price = rs.getDouble("PRICE");
            products.add(new Product(id, productName, price, m));
        }
        statement.close();
        connection.close();
        return products;
    }

    // Busca produto pelo ID
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
            connection.close();
            return new Product(productId, productName, productPrice, m);
        }
        statement.close();
        connection.close();
        return null;
    }

    // Busca produto pelo ID do fabricante
    public static List<Product> searchProductsByManufacturerId(Integer id) throws SQLException, ClassNotFoundException {
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
        connection.close();
        return products;
    }

    // Deleta produto pelo ID
    public static boolean deleteProductById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
        String sql = "DELETE FROM TB_PRODUCTS WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        int affectedRows = statement.executeUpdate();
        statement.close();
        connection.close();
        return affectedRows != 0;
    }

    public static boolean deleteProductByName(String name) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
        String sql = "DELETE FROM TB_PRODUCTS WHERE NAME = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        int affectedRows = statement.executeUpdate();
        statement.close();
        connection.close();
        return affectedRows != 0;
    }

    // Registra fabricante
    public static boolean insertManufacturer(Manufacturer manufacturer) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
        String checkIfExists =  "SELECT * FROM TB_MANUFACTURERS WHERE NAME = ?";
        PreparedStatement statement = connection.prepareStatement(checkIfExists);
        statement.setString(1, manufacturer.getName());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            statement.close();
            connection.close();
            return false;
        }

        String insert = "INSERT INTO TB_MANUFACTURERS(NAME) VALUES (?)";
        statement = connection.prepareStatement(insert);
        statement.setString(1, manufacturer.getName());
        statement.executeUpdate();
        statement.close();
        connection.close();
        return true;
    }

    // Busca fabricante pelo nome
    public static Manufacturer searchManufacturerByName(String name) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();
        String sql = "SELECT * FROM TB_MANUFACTURERS WHERE NAME = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("ID");
            String manufacturerName = rs.getString("NAME");
            statement.close();
            connection.close();
            return new Manufacturer(id, manufacturerName);
        }
        statement.close();
        connection.close();
        return null;
    }

    // Cria tabela usuários no banco de dados
    public static void createUserTable() throws SQLException, ClassNotFoundException {
        String userTableQuery = "CREATE TABLE TB_USERS (id INTEGER IDENTITY PRIMARY KEY, username VARCHAR(255), password VARCHAR(255))";
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(userTableQuery);
        statement.close();
        connection.close();
    };

    // Cria tabela de fabricantes no banco de dados
    public static void createManufacturerTable() throws SQLException, ClassNotFoundException {
        String manufacturerTableQuery = "CREATE TABLE TB_MANUFACTURERS (id INTEGER IDENTITY PRIMARY KEY, name VARCHAR(255))";
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(manufacturerTableQuery);
        statement.close();
        connection.close();
    };

    // Cria tabela de produtos no banco de dados
    public static void createProductTable() throws SQLException, ClassNotFoundException {
        String productTableQuery = "CREATE TABLE TB_PRODUCTS (id INTEGER IDENTITY PRIMARY KEY, name VARCHAR(255), price DOUBLE PRECISION, manufacturer_id INT, FOREIGN KEY (manufacturer_id) REFERENCES TB_MANUFACTURERS(id))";
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(productTableQuery);
        statement.close();
        connection.close();
    };
}