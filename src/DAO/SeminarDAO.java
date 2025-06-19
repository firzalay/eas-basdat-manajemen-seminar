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
}
