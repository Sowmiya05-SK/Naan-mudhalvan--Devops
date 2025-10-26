package org.example.hcms.dao;

import org.example.hcms.db.Database;
import org.example.hcms.model.Complaint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {
    public void save(Complaint c) throws SQLException {
        String sql = "INSERT INTO complaints(name, room, category, description, status) VALUES(?,?,?,?,?)";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getRoom());
            ps.setString(3, c.getCategory());
            ps.setString(4, c.getDescription());
            ps.setString(5, c.getStatus());
            ps.executeUpdate();
        }
    }

    public List<Complaint> findAll() throws SQLException {
        List<Complaint> list = new ArrayList<>();
        String sql = "SELECT id, name, room, category, description, status FROM complaints ORDER BY id DESC";
        try (Connection conn = Database.getInstance().getConnection();
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
}
