package model;

public class Pendaftaran {
    private int idPendaftaran;
    private int idUser;
    private int idSeminar;
    private String statusKelulusan;

    public Pendaftaran(int idPendaftaran, int idUser, int idSeminar, String statusKelulusan) {
        this.idPendaftaran = idPendaftaran;
        this.idUser = idUser;
        this.idSeminar = idSeminar;
        this.statusKelulusan = statusKelulusan;
    }

    public Pendaftaran(int idUser, int idSeminar) {
        this.idUser = idUser;
        this.idSeminar = idSeminar;
    }

    public int getIdPendaftaran() {
        return idPendaftaran;
    }

    public void setIdPendaftaran(int idPendaftaran) {
        this.idPendaftaran = idPendaftaran;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdSeminar() {
        return idSeminar;
    }

    public void setIdSeminar(int idSeminar) {
        this.idSeminar = idSeminar;
    }

    public String getStatusKelulusan() {
        return statusKelulusan;
    }

    public void setStatusKelulusan(String statusKelulusan) {
        this.statusKelulusan = statusKelulusan;
    }
}
