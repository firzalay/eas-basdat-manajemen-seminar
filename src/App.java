import java.util.Scanner;
import DAO.UserDAO;
import model.User;

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
                    UserDAO.validateUserLogin(email, password);
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

}
