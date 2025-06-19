package model;

import java.sql.Date;

public class Seminar {
    private int idSeminar;
    private String temaSeminar;
    private Date tanggal;

    public Seminar(int idSeminar, String temaSeminar, Date tanggal) {
        this.idSeminar = idSeminar;
        this.temaSeminar = temaSeminar;
        this.tanggal = tanggal;
    }

    public Seminar(String temaSeminar, Date tanggal) {
        this.temaSeminar = temaSeminar;
        this.tanggal = tanggal;
    }

    public int getIdSeminar() {
        return idSeminar;
    }

    public void setIdSeminar(int idSeminar) {
        this.idSeminar = idSeminar;
    }

    public String getTemaSeminar() {
        return temaSeminar;
    }

    public void setTemaSeminar(String temaSeminar) {
        this.temaSeminar = temaSeminar;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

}
