package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

import model.Seminar;

public class SeminarDAO {
    public static ArrayList<Seminar> getAllSeminar() {
        ArrayList<Seminar> arrayList = new ArrayList<>();
        String query = "SELECT * FROM tb_seminar";

        try (Connection conn = ConnectionProvider.getCon()) {
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_seminar");
                String tema = rs.getString("tema_seminar");
                Date tanggal = rs.getDate("tanggal");

                Seminar seminar = new Seminar(id, tema, tanggal);
                arrayList.add(seminar);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static void create(Seminar seminar) {
        String query = "INSERT INTO tb_seminar (tema_seminar, tanggal) VALUES (?, ?)";
        try (Connection conn = ConnectionProvider.getCon()) {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, seminar.getTemaSeminar());
            ps.setDate(2, seminar.getTanggal());

            ps.executeUpdate();
            System.out.println("Seminar berhasil ditambahkan.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(Seminar seminar) {
        String query = "UPDATE tb_seminar SET tema_seminar = ?, tanggal = ? WHERE id_seminar = ?";
        try (Connection conn = ConnectionProvider.getCon()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, seminar.getTemaSeminar());
            ps.setDate(2, seminar.getTanggal());
            ps.setInt(3, seminar.getIdSeminar());
            ps.executeUpdate();
            System.out.println("Seminar berhasil diperbarui.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int idSeminar) {
        String query = "DELETE FROM tb_seminar WHERE id_seminar = ?";
        try (Connection conn = ConnectionProvider.getCon()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idSeminar);
            ps.executeUpdate();
            System.out.println("Seminar berhasil dihapus.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void laporanJumlahPesertaPerSeminar() {
        String query = """
                    SELECT
                        s.tema_seminar,
                        COUNT(CASE WHEN p.status_kelulusan = 'lulus' THEN 1 END) AS lulus,
                        COUNT(CASE WHEN p.status_kelulusan = 'tidak lulus' THEN 1 END) AS tidak_lulus
                    FROM
                        tb_pendaftaran p
                    JOIN
                        tb_seminar s ON p.id_seminar = s.id_seminar
                    GROUP BY
                        s.id_seminar
                """;

        try (Connection conn = ConnectionProvider.getCon();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            System.out.println("\n=== Laporan Status Kelulusan per Seminar ===");
            System.out.printf("| %-40s | %-10s | %-13s |\n", "Tema Seminar", "Lulus", "Tidak Lulus");
            System.out.println("--------------------------------------------------------------------------");

            while (rs.next()) {
                String tema = rs.getString("tema_seminar");
                int lulus = rs.getInt("lulus");
                int tidakLulus = rs.getInt("tidak_lulus");

                System.out.printf("| %-40s | %-10d | %-13d |\n", tema, lulus, tidakLulus);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void laporanSeminarPopuler() {
        String query = """
                    SELECT * FROM tb_seminar s
                    WHERE (
                        SELECT COUNT(*) FROM tb_pendaftaran p
                        WHERE p.id_seminar = s.id_seminar
                    ) > 3
                """;

        try (Connection conn = ConnectionProvider.getCon();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            System.out.println("\n=== Laporan Seminar dengan Peserta > 3 ===");
            System.out.printf("| %-10s | %-40s | %-12s |\n", "ID Seminar", "Tema Seminar", "Tanggal");
            System.out.println("-----------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("| %-10d | %-40s | %-12s |\n",
                        rs.getInt("id_seminar"),
                        rs.getString("tema_seminar"),
                        rs.getDate("tanggal").toString());
            }

            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
