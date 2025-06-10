package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public static void validateUserLogin(String email, String password) {
        String query = "SELECT * FROM tb_user WHERE email = ? AND password = ? ";
        try (Connection conn = ConnectionProvider.getCon()) {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, password);
            

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login berhasil!");
            } else {
                System.out.println("Username / Password salah");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
