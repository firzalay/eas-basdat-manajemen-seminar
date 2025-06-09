import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int pilih;
        do {

            menuUtama();
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

    public static void menuUtama() {

        System.out.println("\n=== Sistem Manajemen Seminar ===");
        System.out.println("1. Peserta");
        System.out.println("2. Admin");
        System.out.println("3. Exit");
        System.out.print("Pilih: ");
    }

    public static void menuPeserta(Scanner scanner) {


        int pilihanPeserta;

        System.out.println("\n=== Menu Peserta ===");

        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Kembali");
        System.out.print("Pilih: ");
        pilihanPeserta = scanner.nextInt();

        switch (pilihanPeserta) {
            case 1:
                System.out.println("Test");
                break;
            case 2:
                System.out.println("Test");
                break;
            case 3:
                break;

            default:
                break;
        }
    }

}
