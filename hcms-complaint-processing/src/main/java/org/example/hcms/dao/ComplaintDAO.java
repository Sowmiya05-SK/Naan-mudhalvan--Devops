package org.example.hcms.dao;

import org.example.hcms.db.Database;
import org.example.hcms.model.Complaint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {
    public List<Complaint> findAll() throws SQLException {
        List<Complaint> list = new ArrayList<>();
        String sql = "SELECT * FROM complaints ORDER BY id DESC";
        try (Connection conn = Database.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                list.add(new Complaint(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("room"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("worker")
                ));
            }
        }
        return list;
    }

    public void updateStatusAndWorker(int id, String status, String worker) throws SQLException {
        String sql = "UPDATE complaints SET status=?, worker=? WHERE id=?";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, worker);
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }
}
