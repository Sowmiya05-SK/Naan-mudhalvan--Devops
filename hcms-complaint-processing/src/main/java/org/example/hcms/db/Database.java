package org.example.hcms.db;

import java.sql.*;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:../hcms-complaint-ui/hcms.db"; // point to Student 1 DB
    private static Database instance;

    private Database() {}

    public static synchronized Database getInstance() {
        if (instance == null) instance = new Database();
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
