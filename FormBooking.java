import java.util.Scanner;

public class FormBooking {

    static Scanner sc = new Scanner(System.in);
    static boolean menu = true;
    static String[][] listDataBooking = new String[10][3]; // Assuming a maximum of 10 bookings
    static int bookingCount = 0;

    public static void main(String[] args) {
        do {
            tampilkanMenu();
        } while (menu);
    }

    static void tampilkanMenu() {
        System.out.println("==================================================");
        System.out.println("=                      Menu                      =");
        System.out.println("==================================================\n");
        System.out.println("[1]. Login");
        System.out.println("[0]. Keluar");
        System.out.print("Pilih menu: ");
        int pilihanMenu = sc.nextInt();
        sc.nextLine();

        switch (pilihanMenu) {
            case 1:
                login();
                break;
            case 0:
                menu = false;
                System.out.println("\nAnda telah keluar dari program.");
                break;
            default:
                System.out.println("\nPilihan tidak valid. Silakan pilih lagi.\n");
        }
    }

    static void login() {
        System.out.println("\n==================================================");
        System.out.println("=                     Login                      =");
        System.out.println("==================================================\n");

        String bookingUsername = "user";
        String bookingPassword = "user";

        String adminUsername = "admin";
        String adminPassword = "admin";

        Boolean loginSuccess = false;

        while (!loginSuccess) {
            System.out.println("Login Aplikasi Booking Ruangan");
            System.out.print("Masukkan Username Anda: ");
            String usernameIn = sc.next();
            System.out.print("Masukkan Password Anda: ");
            String passwordIn = sc.next();

            if (usernameIn.equals(bookingUsername) && passwordIn.equals(bookingPassword)) {
                loginSuccess = true;
                System.out.println("\nLogin Berhasil");
                System.out.println("Selamat Datang " + bookingUsername + " di Aplikasi Booking Ruangan");
                userMenu();
            } else if (usernameIn.equals(adminUsername) && passwordIn.equals(adminPassword)) {
                loginSuccess = true;
                System.out.println("\nLogin Berhasil sebagai Admin");
                adminMenu();
            } else {
                System.out.println("Username atau Password yang Anda Masukkan Salah!");
                System.out.println("Silakan Mengisi Username dan Password Anda Kembali!\n");
            }
        }
    }

    static void userMenu() {
        boolean userMenu = true;
        do {
            System.out.println("==================================================");
            System.out.println("=                      Menu                      =");
            System.out.println("==================================================\n");
            System.out.println("[1] Form Booking");
            System.out.println("[0] Logout");
            System.out.print("Pilih menu: ");
            int menuUser = sc.nextInt();
            sc.nextLine();
            switch (menuUser) {
                case 1:
                    userForm();
                    break;
                case 0:
                    userMenu = false;
                    System.out.println("\nAnda berhasil logout.");
                    break;
                default:
                    System.out.println("\nPilihan tidak valid. Silakan pilih lagi.\n");
            }
        } while (userMenu);
    }

    static void userForm() {
        System.out.println("\n==================================================");
        System.out.println("=                 Form Booking                   =");
        System.out.println("==================================================\n");
    
        boolean isNamaValid = false;
    
        while (!isNamaValid) {
            System.out.print("Nama: ");
            String inputNama = sc.next();
            if (!inputNama.trim().isEmpty()) {
                isNamaValid = true;
                listDataBooking[bookingCount][0] = inputNama;
            } else {
                System.out.println("Nama tidak boleh kosong. Silakan masukkan nama lagi.\n");
            }
        }
    
        boolean isAlamatValid = false;
    
        while (!isAlamatValid) {
            System.out.print("Alamat: ");
            String alamat = sc.next();
            if (!alamat.trim().isEmpty()) {
                isAlamatValid = true;
                listDataBooking[bookingCount][1] = alamat;
            } else {
                System.out.println("Alamat tidak boleh kosong. Silakan masukkan lagi.");
            }
        }
    
        boolean isNoHpValid = false;
    
        while (!isNoHpValid) {
            System.out.print("Nomor Telepon: ");
            String noHp = sc.next().trim();
    
            int panjangNoHp = noHp.length();
            if (panjangNoHp == 12 || panjangNoHp == 13) {
                isNoHpValid = true;
                listDataBooking[bookingCount][2] = noHp;
            } else {
                System.out.println("Nomor telepon harus 12 atau 13 angka. Silakan masukkan lagi!\n");
            }
        }
    
        System.out.println("\nData Diri Anda:");
        System.out.println("Nama           : " + listDataBooking[bookingCount][0]);
        System.out.println("Alamat         : " + listDataBooking[bookingCount][1]);
        System.out.println("Nomor Telepon  : " + listDataBooking[bookingCount][2]);
    
        boolean konfirmasiBenar = false;
    
        while (!konfirmasiBenar) {
            System.out.print("Silakan Ketik (Konfirmasi) Untuk Menyimpan Data Anda atau 'Batal' untuk membatalkan: ");
            String konfirmasi = sc.next();
            if (konfirmasi.equalsIgnoreCase("Konfirmasi")) {
                konfirmasiBenar = true;
                System.out.println("Data Diri Anda Telah Tersimpan");
                bookingCount++;
            } else if (konfirmasi.equalsIgnoreCase("Batal")) {
                konfirmasiBenar = true;
                System.out.println("Pendaftaran dibatalkan. Anda akan kembali ke menu.");
            } else {
                System.out.println("Pilihan tidak valid! Silakan coba lagi!\n");
            }
        }
    }

    static void adminMenu() {
        boolean adminMenu = true;
        do {
            System.out.println("==================================================");
            System.out.println("=                  Menu Admin                    =");
            System.out.println("==================================================\n");
            System.out.println("[1]. Data Form Booking.");
            System.out.println("[0]. Logout.");
            System.out.print("Pilih menu: ");
            int menuAdmin = sc.nextInt();
            sc.nextLine();

            switch (menuAdmin) {
                case 1:
                    dataForm();
                    break;
                case 0:
                    adminMenu = false;
                    System.out.println("\nAnda berhasil logout sebagai Admin.");
                    break;
                default:
                    System.out.println("\nPilihan tidak valid. Silakan pilih lagi.\n");
            }
        } while (adminMenu);
    }

    static void dataForm() {
        if (bookingCount == 0) {
            System.out.println("\nBelum ada data booking.");
        } else {
            System.out.println("\nData Form Booking:");
            for (int i = 0; i < bookingCount; i++) {

                System.out.println("Pendaftar ke-" + (i + 1));
                System.out.println("-------------------------------------");
                System.out.println("Nama           : " + listDataBooking[i][0]);
                System.out.println("Alamat         : " + listDataBooking[i][1]);
                System.out.println("Nomor Telepon  : " + listDataBooking[i][2]);
                System.out.println("-------------------------------------");
                System.out.println();
            }
        }

        System.out.println("\n==================================================");
        System.out.println("=                System Admin                    =");
        System.out.println("==================================================\n");
    }
}
