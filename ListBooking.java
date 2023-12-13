import java.util.Scanner;

public class ListBooking {

    static Scanner sc = new Scanner(System.in);
    static boolean menu = true;

    // Array Status ruangan ketika terbooking atau tidak
    static boolean[] ruanganTerbooking = new boolean[3];

    // Array Harga Ruangan
    static int[] hargaRuangan = { 400000, 500000, 600000 };

    /*
     * Fungsi Main
     */
    public static void main(String[] args) {

        do {
            tampilkanMenu();
        } while (menu);
    }

    /*
     * Fungsi Tampilkan Menu
     */
    static void tampilkanMenu() {
        System.out.println("==================================================");
        System.out.println("=                      Menu                      =");
        System.out.println("==================================================\n");
        System.out.println("[1]. List Booking");
        System.out.println("[0]. keluar");
        System.out.print("Pilih menu: ");
        int pilihanMenu = sc.nextInt();
        sc.nextLine();

        // pilihan menu
        switch (pilihanMenu) {
            // pergi ke list booking
            case 1:
                listBooking();
                break;

            // keluar
            case 0:
                menu = false;
                System.out.println("\nAnda telah keluar dari program.");
                break;
            default:
                System.out.println("\nPilihan tidak valid. Silakan pilih lagi.\n");
        }
    }

    /*
     * Fungsi List Booking
     */
    static void listBooking() {

        // Boolean ketika semua kamar terbooking
        boolean semuaKamarTerbooking = true;

        // For-each Status ruangan
        for (boolean status : ruanganTerbooking) {
            if (!status) {
                semuaKamarTerbooking = false;
                break;
            }
        }

        // Kondisi ketika Semua kamar terbooking
        if (semuaKamarTerbooking) {
            System.out.println("\n==================================================");
            System.out.println("=        Maaf, semua kamar telah terbooking       =");
            System.out.println("=   Silakan menunggu hingga ada kamar yang kosong =");
            System.out.println("==================================================\n");
            return;
        }

        int ruang = 0;

        // Perulangan List Booking akan keluar jika hasil true
        boolean isListBooking = false;
        while (!isListBooking) {

            System.out.println("\n==================================================");
            System.out.println("=                 List Booking                   =");
            System.out.println("==================================================\n");

            // List ruangan
            System.out.println("List ruangan yang tersedia pada hari ini\n");

            // Fasilitas ruangan
            String[][] fasilitasRuangan = {
                    // Fasilitas ruang 1
                    { "1. Kamar tidur kapasitas 2 orang",
                            "2. Kamar mandi dalam dengan air panas",
                            "3. Handuk dan peralatan mandi - \n\nHarga: Rp. 400.000" },
                    // Fasilitas ruang 2
                    { "1. Tempat tidur dengan kapasitas 1 orang ",
                            "2. Kamar mandi dalam dengan air panas ",
                            "3. Snack yang telah disediakan didalam kamar \n\n - Harga: Rp. 500.000" },
                    // Fasilitas ruang 3
                    { "1. Kamar tidur kapasitas 1 orang ",
                            "2. Kamar mandi dalam dengan air panas ",
                            "3. Ruangan ber-AC \n\n- Harga: Rp. 600.000" }
            };

            // For-each fasilitas ruangan
            for (int i = 0; i < fasilitasRuangan.length; i++) {

                // Kondisi ketika ruangan belum terbooking
                if (!ruanganTerbooking[i]) {
                    System.out.println("\nRuangan " + (i + 1) + ":");
                    // For-each untuk menampilkan fasilitas ruangan yang belum terbooking
                    for (int j = 0; j < fasilitasRuangan[i].length; j++) {
                        System.out.println("  " + fasilitasRuangan[i][j]);
                    }

                    // Kondisi ketika ruangan telah terbooking
                } else {
                    System.out.println("\nRuangan " + (i + 1) + " (Terbooking)");
                }
            }

            // Pilihan ruangan oleh user
            System.out.print(
                    "\nSilahkan masukan ruangan yang ingin anda pesan (1/2/3) atau (0) untuk membatalkan pilihan: ");
            ruang = sc.nextInt();

            // Kondisi ketika pilihan selain 1 - 3 dan 0
            if ((ruang > 3 || ruang < 1) && ruang != 0) {
                System.out.println(
                        "\nRuang yang anda pilih tidak tersedia, silahkan untuk mencoba kembali!");

                // Kondisi ketika pilihan benar dan ruangan belum terbooking
            } else if (ruang != 0 && !ruanganTerbooking[ruang - 1]) {
                isListBooking = true;
                // Kondisi ketika pengguna memilih ruangan yang sudah dibooking
            } else if (ruang != 0 && ruanganTerbooking[ruang - 1]) {
                System.out.println("\nRuangan " + ruang + " telah terbooking, silahkan pilih ruangan yang lain.");
                // Kondisi ketika pengguna memilih 0 untuk membatalkan pilihan ruangan
            } else if (ruang == 0) {
                System.out.println("\nAnda membatalkan pemilihan ruangan.\n");
                return;
            }
        }

        // Status ruangan yang telah dibooking user menjadi true
        ruanganTerbooking[ruang - 1] = true;

        String konfirmasiRuangan;
        Boolean isKonfirmasiRuangan = false;

        // Perulangan konfirmasi pemilihan ruangan
        while (!isKonfirmasiRuangan) {

            System.out.println("\n==================================================");
            System.out.println("=                   Konfirmasi                   =");
            System.out.println("==================================================");

            // Pilihan user
            System.out.print("\nJika anda telah yakin dengan ruangan " + ruang + " ketik 'y', jika batal ketik 't': ");
            konfirmasiRuangan = sc.next();

            // Kondisi ketika user memilih y maka ruangan berhasil dipesan
            if (konfirmasiRuangan.equalsIgnoreCase("y")) {
                System.out.println("\nAnda telah berhasil memesan ruangan " + ruang);
                isKonfirmasiRuangan = true;

                // Kondisi ketika user memilih t maka ruangan batal dipesan
            } else if (konfirmasiRuangan.equalsIgnoreCase("t")) {
                System.out.println("\nAnda membatalkan pemesanan ruangan " + ruang);

                // Status ruangan yang telah dibooking user menjadi false
                ruanganTerbooking[ruang - 1] = false;
                isKonfirmasiRuangan = true;

                // Kondisi ketika user memilih input selain y dan t
            } else {
                System.out.println("\nInput yang anda masukan salah silahkan coba lagi!");
            }
        }
    }
}
