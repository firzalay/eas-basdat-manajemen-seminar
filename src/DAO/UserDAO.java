package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static User validatedUserLogin(String email, String password) {
        String query = "SELECT * FROM tb_user WHERE email = ? AND password = ? ";
        User user = null;
        try (Connection conn = ConnectionProvider.getCon()) {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("id_user"),
                        rs.getString("nama"),
                        rs.getString("email"),
                        rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static User validatedAdminLogin(String email, String password) {
        String query = "SELECT * FROM tb_user WHERE email = ? AND password = ? AND id_role = ? ";
        User user = null;
        try (Connection conn = ConnectionProvider.getCon()) {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, password);
            ps.setInt(3, 2);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("id_user"),
                        rs.getString("nama"),
                        rs.getString("email"),
                        rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void callCetakSertifikat(int userId, int seminarId) {
        try (Connection conn = ConnectionProvider.getCon()) {
            CallableStatement stmt = conn.prepareCall("{CALL generate_sertifikat(?, ?)}");
            stmt.setInt(1, userId);
            stmt.setInt(2, seminarId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
