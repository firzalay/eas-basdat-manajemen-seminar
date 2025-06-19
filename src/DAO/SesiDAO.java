package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

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
                        rs.getString("id_sesi"),
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

    public static ArrayList<Sesi> getAllSesi() {
        ArrayList<Sesi> arrayList = new ArrayList<>();
        String query = "SELECT * FROM tb_sesi";

        try (Connection conn = ConnectionProvider.getCon()) {
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id_sesi");
                String namaPembicara = rs.getString("nama_pembicara");
                String judulSesi = rs.getString("judul_sesi");
                Time waktuMulai = rs.getTime("waktu_mulai");
                Time waktuSelesai = rs.getTime("waktu_selesai");
                int idSeminar = rs.getInt("id_seminar");

                Sesi sesi = new Sesi(id, namaPembicara, judulSesi, waktuMulai, waktuSelesai, idSeminar);
                arrayList.add(sesi);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static void create(Sesi sesi) {
        String query = "INSERT INTO tb_sesi (id_sesi, nama_pembicara, judul_sesi, waktu_mulai, waktu_selesai, id_seminar) VALUES (?,?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionProvider.getCon()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, sesi.getIdSesi());
            ps.setString(2, sesi.getNamaPembicara());
            ps.setString(3, sesi.getJudulSesi());
            ps.setTime(4, sesi.getWaktuMulai());
            ps.setTime(5, sesi.getWaktuSelesai());
            ps.setInt(6, sesi.getIdSeminar());
            ps.executeUpdate();
            System.out.println("Sesi berhasil ditambahkan.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
