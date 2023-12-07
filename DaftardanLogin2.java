import java.util.Scanner;

public class DaftardanLogin2 {

    static Scanner sc = new Scanner(System.in);
    static String[][] userDatabase = new String[1000][2];
    static int userCount = 0;

    public static void main(String[] args) {
        boolean isUserTerdaftar = false;

        do {
            System.out.println("\n==================================================");
            System.out.println("=              Silahkan Pilih Menu               =");
            System.out.println("==================================================\n");
            System.out.println("[1] Daftar Akun");
            System.out.println("[2] Login");
            System.out.println("[3] Keluar");
            System.out.print("Pilih menu: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    daftarAkun();
                    break;
                case 2:
                    isUserTerdaftar = login();
                    break;
                case 3:
                    System.out.println("Terima kasih!");
                    System.exit(0);
                default:
                    System.out.println("\nPilihan tidak valid. Silakan coba lagi.");
            }
        } while (!isUserTerdaftar);

        System.out.println("Anda telah berhasil login. Selamat datang di Aplikasi Booking Ruangan!\n");
    }

    static void daftarAkun() {
        System.out.println("\n==================================================");
        System.out.println("=                  Daftar Akun                   =");
        System.out.println("==================================================\n");

        System.out.print("Masukan Username: ");
        String username = sc.nextLine();

        System.out.print("Masukan Password: ");
        String password = sc.nextLine();

        // Menambahkan informasi akun ke database
        userDatabase[userCount][0] = username;
        userDatabase[userCount][1] = password;

        userCount++;

        System.out.println("\nPendaftaran akun telah berhasil! Silahkan memulai login di Aplikasi Booking Ruangan.");
    }

    static boolean login() {
        System.out.println("\n==================================================");
        System.out.println("=                     Login                      =");
        System.out.println("==================================================\n");

        int percobaanLogin = 0;

        while (percobaanLogin < 3) {

            System.out.print("Masukan Username: ");
            String username = sc.nextLine();

            System.out.print("Masukan Password: ");
            String password = sc.nextLine();

            // Memeriksa apakah informasi login sesuai dengan database
            for (int i = 0; i < userCount; i++) {

                if (username.equals(userDatabase[i][0]) && password.equals(userDatabase[i][1])) {
                    System.out.println("\nLogin Berhasil");
                    System.out.println("Selamat Datang " + username + " di Aplikasi Booking Ruangan\n");
                    userMenu();
                    return true;
                } else {
                    percobaanLogin++;
                    System.out.println("\nUsername atau Password yang Anda Masukkan Salah!, Silahkan coba lagi!");
                    System.out.println("Sisa percobaan login: " + (3 - percobaanLogin));

                    if (percobaanLogin < 3) {
                    } else {
                        System.out.println("\nBatas percobaan login telah habis!\n");
                        System.exit(0); 
                    }

                }
            }
        }

        System.out.println("Username atau Password yang Anda masukam Salah. Silakan coba lagi.");
        return false;

    }

    static void userMenu() {
        boolean userMenu = true;
        do {
            System.out.println("==================================================");
            System.out.println("=                      Menu                      =");
            System.out.println("==================================================\n");
            System.out.println("[0] logout");
            System.out.print("Pilih menu: ");
            int menuUser = sc.nextInt();
            sc.nextLine();
            switch (menuUser) {

                case 0:
                    userMenu = false;
                    System.out.println("\nAnda berhasil logout.");
                    break;
                default:
                    System.out.println("\nPilihan tidak valid. Silakan pilih lagi.\n");
            }
        } while (userMenu);
    }

}
