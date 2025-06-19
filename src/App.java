import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

import DAO.KehadiranDAO;
import DAO.PendaftaranDAO;
import DAO.SeminarDAO;
import DAO.SesiDAO;
import DAO.UserDAO;
import model.Seminar;
import model.User;
import model.Kehadiran;
import model.Pendaftaran;
import model.Sesi;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int pilih;
        do {
            System.out.println("\n=== Sistem Manajemen Seminar ===");
            System.out.println("1. Peserta");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            System.out.print("Pilih: ");
            pilih = scanner.nextInt();

            switch (pilih) {
                case 1:
                    menuPeserta(scanner);
                    break;

                case 2:
                    menuAdmin(scanner);
                    break;

                case 3:
                    break;

                default:
                    System.out.println("Pilihan anda tidak valid!");
                    break;
            }
        } while (pilih != 3);

    }

    public static void menuPeserta(Scanner scanner) {

        int pilihanPeserta;
        String email, nama, password;
        String emailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";

        do {
            System.out.println("\n=== Menu Peserta ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Kembali");
            System.out.print("Pilih: ");
            pilihanPeserta = scanner.nextInt();

            switch (pilihanPeserta) {
                case 1:
                    System.out.print("Masukkan email: ");
                    email = scanner.next();

                    if (!email.matches(emailPattern)) {
                        System.out.println("Format email salah!");
                        break;
                    }

                    System.out.print("Masukkan password: ");
                    password = scanner.next();

                    User loggedInUser = UserDAO.validatedUserLogin(email, password);

                    if (loggedInUser == null) {
                        System.out.println("Email / Password salah!");
                    } else {
                        System.out.println("Login Berhasil!");

                        int menuPesertaLogin = 0;

                        do {
                            System.out.println("\n=== Menu Utama Peserta ===");
                            System.out.println("1. Daftar Seminar");
                            System.out.println("2. Absensi");
                            System.out.println("3. Logout");
                            System.out.print("Pilih: ");
                            menuPesertaLogin = scanner.nextInt();

                            switch (menuPesertaLogin) {
                                case 1:
                                    menuListSeminar(scanner, loggedInUser);
                                    break;
                                case 2:
                                    menuAbsensi(scanner, loggedInUser);
                                    break;
                                case 3:
                                    System.out.println("Logout berhasil!");
                                    break;
                                default:
                                    System.out.println("Pilihan tidak tersedia!");
                                    break;
                            }
                        } while (menuPesertaLogin != 3);
                    }

                    break;
                case 2:
                    System.out.print("Masukkan nama lengkap: ");
                    scanner.nextLine();
                    nama = scanner.nextLine();

                    System.out.print("Masukkan email: ");
                    email = scanner.next();

                    if (!email.matches(emailPattern)) {
                        System.out.println("Format email salah!");
                        break;
                    }

                    System.out.print("Masukkan password: ");
                    password = scanner.next();

                    User userBaru = new User(nama, email, password, 1);

                    UserDAO.create(userBaru);

                    break;
                case 3:
                    break;
                default:
                    System.out.println("Pilihan anda tidak valid!");
                    break;
            }
        } while (pilihanPeserta != 3);

    }

    public static void menuListSeminar(Scanner scanner, User loggedInUser) {
        int idSeminar = 0;
        System.out.println("\n=== List Seminar ===");

        int nomer = 1;
        String format = "| %-4s | %-6s | %-50s | %-15s |\n";
        System.out.println("+------+--------+----------------------------------------------------+-----------------+");
        System.out.printf(format, "No", "ID", "Tema Seminar", "Tanggal");
        System.out.println("+------+--------+----------------------------------------------------+-----------------+");

        for (Seminar seminar : SeminarDAO.getAllSeminar()) {
            System.out.printf(format,
                    nomer,
                    seminar.getIdSeminar(),
                    seminar.getTemaSeminar(),
                    seminar.getTanggal().toString());
            nomer++;
        }

        System.out.println("+------+--------+----------------------------------------------------+-----------------+");

        System.out.print("Masukkan ID Seminar: ");
        idSeminar = scanner.nextInt();

        Pendaftaran pendaftaranBaru = new Pendaftaran(loggedInUser.getIdUser(), idSeminar);

        PendaftaranDAO.create(pendaftaranBaru);

    }

    public static void menuAbsensi(Scanner scanner, User loggedInUser) {
        int idSesi = 0, idUser;

        while (true) {
            System.out.println("\n=== Menu Absensi === ");
            System.out.print("Masukkan Kode sesi: ");
            idSesi = scanner.nextInt();

            Sesi sesi = SesiDAO.getSesiById(idSesi);
            idUser = loggedInUser.getIdUser();

            if (sesi == null) {
                System.out.println("ID sesi tidak ditemukan! coba lagi.");
            } else {
                Kehadiran catatKehadiran = new Kehadiran(idUser, idSesi);
                KehadiranDAO.create(catatKehadiran);
                break;
            }

        }

    }

    public static void menuAdmin(Scanner scanner) {
        int pilihanAdmin;
        String email, password;
        String emailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";

        do {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Login");
            System.out.println("2. Kembali");
            System.out.print("Pilih: ");
            pilihanAdmin = scanner.nextInt();

            switch (pilihanAdmin) {
                case 1:
                    System.out.print("Masukkan email: ");
                    email = scanner.next();

                    if (!email.matches(emailPattern)) {
                        System.out.println("Format email salah!");
                        break;
                    }

                    System.out.print("Masukkan password: ");
                    password = scanner.next();

                    User loggedInUser = UserDAO.validatedAdminLogin(email, password);

                    if (loggedInUser == null) {
                        System.out.println("Email / Password salah!");
                    } else {
                        System.out.println("Login Admin Berhasil!");

                        int menuAdminLogin = 0;

                        do {
                            System.out.println("\n=== Menu Admin ===");
                            System.out.println("1. Kelola Seminar");
                            System.out.println("2. Kelola Sesi");
                            System.out.println("3. Cetak Sertifikat");
                            System.out.println("4. Laporan");
                            System.out.println("5. Kembali");
                            System.out.print("Pilih: ");
                            menuAdminLogin = scanner.nextInt();

                            switch (menuAdminLogin) {
                                case 1:
                                    kelolaSeminar(scanner);
                                    break;
                                case 2:
                                    kelolaSesi(scanner);
                                    break;
                                case 3:

                                    break;
                                case 4:

                                    break;
                                case 5:
                                    System.out.println("Logout");
                                    break;
                                default:
                                    System.out.println("Pilihan tidak valid!");
                                    break;
                            }

                        } while (menuAdminLogin != 5);
                    }

                    break;
                case 2:
                    break;
                default:
                    System.out.println("Pilihan anda tidak valid!");
                    break;
            }
        } while (pilihanAdmin != 2);
    }

    public static void kelolaSeminar(Scanner scanner) {
        int pilih;
        do {
            System.out.println("\n=== Kelola Seminar ===");
            System.out.println("1. Lihat Seminar");
            System.out.println("2. Tambah Seminar");
            System.out.println("3. Ubah Seminar");
            System.out.println("4. Hapus Seminar");
            System.out.println("5. Kembali");
            System.out.print("Pilih: ");
            pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1:
                    int nomer = 1;
                    String format = "| %-4s | %-6s | %-50s | %-15s |\n";
                    System.out.println(
                            "+------+--------+----------------------------------------------------+-----------------+");
                    System.out.printf(format, "No", "ID", "Tema Seminar", "Tanggal");
                    System.out.println(
                            "+------+--------+----------------------------------------------------+-----------------+");

                    for (Seminar seminar : SeminarDAO.getAllSeminar()) {
                        System.out.printf(format,
                                nomer,
                                seminar.getIdSeminar(),
                                seminar.getTemaSeminar(),
                                seminar.getTanggal().toString());
                        nomer++;
                    }

                    System.out.println(
                            "+------+--------+----------------------------------------------------+-----------------+");
                    break;
                case 2:
                    System.out.print("Tema seminar: ");
                    String tema = scanner.nextLine();
                    System.out.print("Tanggal (YYYY-MM-DD): ");
                    String tgl = scanner.next();
                    Seminar seminarBaru = new Seminar(tema, Date.valueOf(tgl));
                    SeminarDAO.create(seminarBaru);
                    break;
                case 3:
                    System.out.print("ID seminar yang akan diubah: ");
                    int idUbah = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Tema baru: ");
                    String temaBaru = scanner.nextLine();
                    System.out.print("Tanggal baru (YYYY-MM-DD): ");
                    String tanggalBaru = scanner.next();
                    Seminar ubahSeminar = new Seminar(idUbah, temaBaru, Date.valueOf(tanggalBaru));
                    SeminarDAO.update(ubahSeminar);
                    break;
                case 4:
                    System.out.print("ID seminar yang akan dihapus: ");
                    int idHapus = scanner.nextInt();
                    SeminarDAO.delete(idHapus);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Pilihan anda tidak valid!");
            }
        } while (pilih != 5);
    }

    public static void kelolaSesi(Scanner scanner) {
        int pilih;
        do {
            System.out.println("\n=== Kelola Sesi ===");
            System.out.println("1. Lihat Sesi");
            System.out.println("2. Tambah Sesi");
            System.out.println("3. Ubah Sesi");
            System.out.println("4. Hapus Sesi");
            System.out.println("5. Kembali");
            System.out.print("Pilih: ");
            pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1:
                    int nomor = 1;
                    String format = "| %-3s | %-8s | %-25s | %-50s | %-8s | %-8s | %-10s |\n";

                    System.out.println(
                            "+-----+----------+---------------------------+----------------------------------------------------+----------+----------+------------+");
                    System.out.printf(format, "No", "ID", "Pembicara", "Judul Sesi", "Mulai", "Selesai", "ID Seminar");
                    System.out.println(
                            "+-----+----------+---------------------------+----------------------------------------------------+----------+----------+------------+");

                    for (Sesi sesi : SesiDAO.getAllSesi()) {
                        System.out.printf(format,
                                nomor,
                                sesi.getIdSesi(), 
                                sesi.getNamaPembicara(),
                                sesi.getJudulSesi(),
                                sesi.getWaktuMulai().toString(),
                                sesi.getWaktuSelesai().toString(),
                                sesi.getIdSeminar());
                        nomor++;
                    }

                    System.out.println(
                            "+-----+----------+---------------------------+----------------------------------------------------+----------+----------+------------+");

                    break;
                case 2:
                    System.out.print("Kode Sesi: ");
                    String kodeSesi = scanner.next();
                    System.out.print("ID Seminar: ");
                    int idSeminar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nama Pembicara: ");
                    String pembicara = scanner.nextLine();
                    System.out.print("Judul Sesi: ");
                    String judul = scanner.nextLine();
                    System.out.print("Waktu Mulai (HH:MM:SS): ");
                    String waktuMulai = scanner.next();
                    System.out.print("Waktu Selesai (HH:MM:SS): ");
                    String waktuSelesai = scanner.next();
                    Sesi sesiBaru = new Sesi(kodeSesi, pembicara, judul, Time.valueOf(waktuMulai),
                            Time.valueOf(waktuSelesai),
                            idSeminar);
                    SesiDAO.create(sesiBaru);
                    break;
                case 3:

                    break;
                case 4:

                    break;
            }
        } while (pilih != 5);
    }
}
