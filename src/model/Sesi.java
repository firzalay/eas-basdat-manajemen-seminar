package model;

import java.sql.Time;

public class Sesi {
    private String idSesi;

    private String namaPembicara;
    private String judulSesi;
    private Time waktuMulai;
    private Time waktuSelesai;
    private int idSeminar;

    public Sesi(String idSesi, String namaPembicara, String judulSesi, Time waktuMulai, Time waktuSelesai, int idSeminar) {
        this.idSesi = idSesi;
        this.namaPembicara = namaPembicara;
        this.judulSesi = judulSesi;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
        this.idSeminar = idSeminar;
    }

    public Sesi(String namaPembicara, String judulSesi, Time waktuMulai, Time waktuSelesai, int idSeminar) {
        this.namaPembicara = namaPembicara;
        this.judulSesi = judulSesi;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
        this.idSeminar = idSeminar;
    }

    public String getIdSesi() {
        return idSesi;
    }

    public void setIdSesi(String idSesi) {
        this.idSesi = idSesi;
    }

    public String getNamaPembicara() {
        return namaPembicara;
    }

    public void setNamaPembicara(String namaPembicara) {
        this.namaPembicara = namaPembicara;
    }

    public String getJudulSesi() {
        return judulSesi;
    }

    public void setJudulSesi(String judulSesi) {
        this.judulSesi = judulSesi;
    }

    public Time getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(Time waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public Time getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(Time waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public int getIdSeminar() {
        return idSeminar;
    }

    public void setIdSeminar(int idSeminar) {
        this.idSeminar = idSeminar;
    }
}
