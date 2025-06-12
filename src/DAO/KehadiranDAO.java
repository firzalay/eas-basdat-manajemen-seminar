package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Kehadiran;

public class KehadiranDAO {
    public static void create(Kehadiran kehadiran) {
        String query = "INSERT INTO tb_kehadiran (id_user, id_sesi) VALUES (?, ?)";
        try (Connection conn = ConnectionProvider.getCon()) {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, kehadiran.getIdUser());
            ps.setInt(2, kehadiran.getIdSesi());

            ps.executeUpdate();
            System.out.println("Kehadiran berhasil di catat!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
