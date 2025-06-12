package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Sesi;

public class SesiDAO {
    public static Sesi getSesiById(int idSesi) {
        String sql = "SELECT * FROM tb_sesi WHERE id_sesi = ?";
        Sesi sesi = null;
        try (Connection conn = ConnectionProvider.getCon()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idSesi);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                sesi = new Sesi(
                        rs.getInt("id_sesi"),
                        rs.getString("nama_pembicara"),
                        rs.getString("judul_sesi"),
                        rs.getTime("waktu_mulai"),
                        rs.getTime("waktu_selesai"),
                        rs.getInt("id_seminar"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sesi;
    }
}
