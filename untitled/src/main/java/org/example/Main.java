package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseManager.createUserTable();
        DatabaseManager.createManufacturerTable();
        DatabaseManager.createProductTable();
    }
}