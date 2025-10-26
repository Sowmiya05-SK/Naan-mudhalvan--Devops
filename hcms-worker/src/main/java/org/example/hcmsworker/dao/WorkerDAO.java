package org.example.hcmsworker.dao;

import org.example.hcmsworker.model.Complaint;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAO {
    // use system property "hcms.db.path" or fallback to relative path
    private static final String DB_PATH = System.getProperty("hcms.db.path",
            Paths.get("..", "hcms-complaint-ui", "hcms.db").toString());
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;

    public List<Complaint> findAll() throws SQLException {
        List<Complaint> list = new ArrayList<>();
        String sql = "SELECT * FROM complaints ORDER BY id DESC";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Complaint(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("room"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status")
                ));
            }
        }
        return list;
    }

    public void updateStatus(int id, String status) throws SQLException {
        String sql = "UPDATE complaints SET status=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void addRemarks(int id, String remarks) throws SQLException {
        // append a newline + remark using parameter to avoid issues with quoting
        String sql = "UPDATE complaints SET description = description || ? WHERE id=?";
        String toAppend = System.lineSeparator() + "[Worker Remark]: " + remarks;
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, toAppend);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }
}