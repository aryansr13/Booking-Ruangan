import java.util.Scanner;

public class FiturReservasiRuanganRapatBeta {

    static Scanner sc = new Scanner(System.in);
    static boolean menu = true;

    // Menambah array untuk menyimpan status pemesanan setiap ruangan
    static boolean[] ruangRptTerbooking = new boolean[3];

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
        System.out.println("[0]. keluar");
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

        String bookingUsername = "user";
        String bookingPassword = "user";

        int percobaanLogin = 0; // Menyimpan jumlah percobaan login

        while (percobaanLogin < 3) {

            System.out.println("\n==================================================");
            System.out.println("=                     Login                      =");
            System.out.println("==================================================\n");

            System.out.print("Masukan Username Anda : ");
            String usernameIn = sc.next();
            System.out.print("Masukan Password Anda : ");
            String passwordIn = sc.next();

            if (usernameIn.equals(bookingUsername) && passwordIn.equals(bookingPassword)) {
                System.out.println("\nLogin Berhasil");
                System.out.println("Selamat Datang di Aplikasi Booking Ruangan\n");
                userMenu();
                return;
            } else {
                percobaanLogin++;
                System.out.println("\nUsername atau Password yang Anda Masukkan Salah!");
                System.out.println("Sisa percobaan login: " + (3 - percobaanLogin));

                if (percobaanLogin < 3) {
                } else {
                    System.out.println("\nAnda telah melebihi batas percobaan login.\n");
                    System.exit(0); // Keluar dari program jika sudah melebihi batas percobaan
                }
            }
        }
    }

    static void userMenu() {
        boolean userMenu = true;
        do {
            System.out.println("==================================================");
            System.out.println("=                      Menu                      =");
            System.out.println("==================================================\n");
            System.out.println("[3] List Booking");
            System.out.println("[0] logout");
            System.out.print("Pilih menu: ");
            int menuUser = sc.nextInt();
            sc.nextLine();
            switch (menuUser) {
                case 3:
                    listBooking();
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

    static void listBooking() {

        boolean semuarptTerbooking = true;

        for (boolean status : ruangRptTerbooking) {
            if (!status) {
                semuarptTerbooking = false;
                break;
            }
        }

        if (semuarptTerbooking) {
            System.out.println("\n==================================================");
            System.out.println("=        Maaf, semua ruang telah terbooking       =");
            System.out.println("=   Silakan menunggu hingga ada kamar yang kosong =");
            System.out.println("==================================================\n");
            return;
        }

        int ruang = 0;

        boolean isListBooking = false;
        while (!isListBooking) {

            System.out.println("\n==================================================");
            System.out.println("=                Reservasi Rapat                 =");
            System.out.println("==================================================\n");

            System.out.println("List ruang rapat yang tersedia pada hari ini\n");
            for (int i = 0; i < ruangRptTerbooking.length; i++) {
                System.out.println((i + 1) + ". Ruang rapat " + (i + 1) + (ruangRptTerbooking[i] ? " (Terbooking)" : ""));
            }

            System.out.print("Silahkan masukan ruang rapat yang ingin anda pesan (1/2/3): ");
            ruang = sc.nextInt();

            if (ruang > 3 || ruang < 1 || ruangRptTerbooking[ruang - 1]) {
                System.out.println(
                        "\nRuang rapat yang anda pilih tidak tersedia atau sudah terbooking, silahkan untuk mencoba kembali!");
            } else {
                isListBooking = true;
            }
        }

        // Set status ruangan yang telah dibooking menjadi true
        ruangRptTerbooking[ruang - 1] = true;

        Boolean isDetailRuangan = false;
        while (!isDetailRuangan) {

            System.out.print("\nApakah Anda Ingin Melihat Detail Fasilitas Ruang rapat " + ruang + " (y/t)?: ");
            String detail = sc.next();

            if (detail.equalsIgnoreCase("y")) {

                if (ruang >= 1 && ruang <= 3) {
                    System.out.println("\n==================================================");
                    System.out.println("=          Detail fasilitas ruang rapat " + ruang + "         =");
                    System.out.println("==================================================\n");

                    String fsltsRpt[][] = new String[3][3];
                    fsltsRpt[0][0] = "1. Kapasitas ruangan untuk 8 orang";
                    fsltsRpt[0][1] = "2. Ruangan Ber-AC";
                    fsltsRpt[0][2] = "3. Proyektor";
                    fsltsRpt[1][0] = "1. Kapasitas ruangan untuk 10 orang";
                    fsltsRpt[1][1] = "2. Ruangan exslusif";
                    fsltsRpt[1][2] = "3. Proyektor";
                    fsltsRpt[2][0] = "1. Kapasitas ruaangan untuk 12 orang";
                    fsltsRpt[2][1] = "2. Proyektor";
                    fsltsRpt[2][2] = "3. Snack dan makanan untuk sarapan";

                    for (int i = 0; i < fsltsRpt[ruang - 1].length; i++) {
                        System.out.println(fsltsRpt[ruang - 1][i]);
                    }
                }
                isDetailRuangan = true;

            } else if (detail.equalsIgnoreCase("t")) {
                isDetailRuangan = true;

            } else {
                System.out.println("\nInput yang anda masukan salah, silahkan coba lagi!");
            }
        }

        String konfirmasiRuangan;
        Boolean isKonfirmasiRuangan = false;
        while (!isKonfirmasiRuangan) {

            System.out.println("\n==================================================");
            System.out.println("=                   Konfirmasi                   =");
            System.out.println("==================================================");

            System.out.print("\nJika anda telah yakin dengan ruangan rapat " + ruang + " ketik (konfirmasi/batal): ");
            konfirmasiRuangan = sc.next();

            if (konfirmasiRuangan.equalsIgnoreCase("konfirmasi")) {
                System.out.println("\nAnda telah berhasil memesan ruangan rapat " + ruang);
                isKonfirmasiRuangan = true;

            } else if (konfirmasiRuangan.equalsIgnoreCase("batal")) {
                System.out.println("\nAnda membatalkan pemesanan ruangan rapat" + ruang);
                isKonfirmasiRuangan = true;

            } else {
                System.out.println("\nInput yang anda masukan salah silahkan coba lagi!");
            }
        }

        System.out.println("\n==================================================");
        System.out.println("=                  System out                    =");
        System.out.println("==================================================\n");
    }
}
