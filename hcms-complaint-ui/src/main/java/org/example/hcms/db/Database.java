package org.example.hcms.db;

import java.sql.*;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:hcms.db";
    private static Database instance;

    private Database() {
        init();
    }

    public static synchronized Database getInstance() {
        if (instance == null) instance = new Database();
        return instance;
    }

    private void init() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS complaints (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL, room TEXT, category TEXT, description TEXT, status TEXT)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
