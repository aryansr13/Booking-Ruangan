import java.util.Scanner;

public class DaftardanLoginBookingRuangan {

    static Scanner scanner = new Scanner(System.in);
    static String[][] userDatabase = new String[100][2];  // Adjust the array size as needed
    static int userCount = 0;

    public static void main(String[] args) {
        boolean isUserAuthenticated = false;

        do {
            System.out.println("Selamat datang!");
            System.out.println("1. Daftar Akun");
            System.out.println("2. Login");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    isUserAuthenticated = login();
                    break;
                case 3:
                    System.out.println("Terima kasih!");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (!isUserAuthenticated);

        // Jika user berhasil login, tambahkan menu atau tindakan selanjutnya di sini
        System.out.println("Anda berhasil login. Selamat datang!");
    }

    static void register() {
        System.out.println("\nSilakan masukkan informasi akun:");

        System.out.print("Nama Pengguna: ");
        String username = scanner.nextLine();

        System.out.print("Kata Sandi: ");
        String password = scanner.nextLine();

        // Menambahkan informasi akun ke database
        userDatabase[userCount][0] = username;
        userDatabase[userCount][1] = password;

        userCount++;

        System.out.println("Pendaftaran berhasil! Silakan login dengan akun Anda.");
    }

    static boolean login() {
        System.out.println("\nSilakan masukkan informasi login:");

        System.out.print("Nama Pengguna: ");
        String username = scanner.nextLine();

        System.out.print("Kata Sandi: ");
        String password = scanner.nextLine();

        // Memeriksa apakah informasi login sesuai dengan database
        for (int i = 0; i < userCount; i++) {
            if (username.equals(userDatabase[i][0]) && password.equals(userDatabase[i][1])) {
                System.out.println("Login berhasil!");
                return true;
            }
        }

        System.out.println("Nama Pengguna atau Kata Sandi tidak valid. Silakan coba lagi.");
        return false;
    }
}
