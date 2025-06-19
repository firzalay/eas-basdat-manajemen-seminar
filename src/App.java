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

}
