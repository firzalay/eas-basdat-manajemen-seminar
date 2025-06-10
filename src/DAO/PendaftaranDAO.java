package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Pendaftaran;

public class PendaftaranDAO {
    public static void create(Pendaftaran pendaftaranBaru) {
        String query = "INSERT INTO tb_pendaftaran (id_user, id_seminar) VALUES (?, ?)";
        try (Connection conn = ConnectionProvider.getCon()) {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, pendaftaranBaru.getIdUser());
            ps.setInt(2, pendaftaranBaru.getIdSeminar());

            ps.executeUpdate();
            System.out.println("Pendaftaran seminar berhasil!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
