package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.User;

public class UserDAO {
    public static void create(User user) {
        String query = "INSERT INTO tb_user (nama, email, password, id_role) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionProvider.getCon()) {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, user.getNama());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getIdRole());

            ps.executeUpdate();
            System.out.println("Pendaftaran berhasil, silahkan login!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
