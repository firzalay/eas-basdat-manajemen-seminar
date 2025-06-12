package model;

public class Kehadiran {
    private int idUser;
    private int idSesi;
    private int statusKehadiran;
    
    public Kehadiran(int idUser, int idSesi) {
        this.idUser = idUser;
        this.idSesi = idSesi;
    }
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIdSesi() {
        return idSesi;
    }
    public void setIdSesi(int idSesi) {
        this.idSesi = idSesi;
    }
    public int getStatusKehadiran() {
        return statusKehadiran;
    }
    public void setStatusKehadiran(int statusKehadiran) {
        this.statusKehadiran = statusKehadiran;
    }



}
