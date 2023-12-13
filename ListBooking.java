import java.util.Scanner;

public class ListBooking {

    static Scanner sc = new Scanner(System.in);
    static boolean menu = true;

    // Menambah array untuk menyimpan status pemesanan setiap ruangan
    static boolean[] ruanganTerbooking = new boolean[3];

    static int[] hargaRuangan = { 400000, 500000, 600000 };

    public static void main(String[] args) {

        do {
            tampilkanMenu();
        } while (menu);
    }

    static void tampilkanMenu() {
        System.out.println("==================================================");
        System.out.println("=                      Menu                      =");
        System.out.println("==================================================\n");
        System.out.println("[1]. List Booking");
        System.out.println("[0]. keluar");
        System.out.print("Pilih menu: ");
        int pilihanMenu = sc.nextInt();
        sc.nextLine();

        switch (pilihanMenu) {
            case 1:
                listBooking();
                break;
            case 0:
                menu = false;
                System.out.println("\nAnda telah keluar dari program.");
                break;
            default:
                System.out.println("\nPilihan tidak valid. Silakan pilih lagi.\n");
        }
    }

    static void listBooking() {

        boolean semuaKamarTerbooking = true;

        for (boolean status : ruanganTerbooking) {
            if (!status) {
                semuaKamarTerbooking = false;
                break;
            }
        }

        if (semuaKamarTerbooking) {
            System.out.println("\n==================================================");
            System.out.println("=        Maaf, semua kamar telah terbooking       =");
            System.out.println("=   Silakan menunggu hingga ada kamar yang kosong =");
            System.out.println("==================================================\n");
            return;
        }

        int ruang = 0;

        boolean isListBooking = false;
        while (!isListBooking) {

            System.out.println("\n==================================================");
            System.out.println("=                 List Booking                   =");
            System.out.println("==================================================\n");

            System.out.println("List ruangan yang tersedia pada hari ini\n");

            String[][] fasilitasRuangan = {
                    { "1. Kamar tidur kapasitas 2 orang",
                            "2. Kamar mandi dalam dengan air panas",
                            "3. Handuk dan peralatan mandi - \n\nHarga: Rp. 400.000" },
                    { "1. Tempat tidur dengan kapasitas 1 orang ",
                            "2. Kamar mandi dalam dengan air panas ",
                            "3. Snack yang telah disediakan didalam kamar \n\n - Harga: Rp. 500.000" },
                    { "1. Kamar tidur kapasitas 1 orang ",
                            "2. Kamar mandi dalam dengan air panas ",
                            "3. Ruangan ber-AC \n\n- Harga: Rp. 600.000" }
            };

            for (int i = 0; i < fasilitasRuangan.length; i++) {
                if (!ruanganTerbooking[i]) {
                    System.out.println("\nRuangan " + (i + 1) + ":");
                    for (int j = 0; j < fasilitasRuangan[i].length; j++) {
                        System.out.println("  " + fasilitasRuangan[i][j]);
                    }
                } else {
                    System.out.println("\nRuangan " + (i + 1) + " (Terbooking)");
                }
            }

            System.out.print("\nSilahkan masukan ruangan yang ingin anda pesan (1/2/3): ");
            ruang = sc.nextInt();

            if (ruang > 3 || ruang < 1 || ruanganTerbooking[ruang - 1]) {
                System.out.println(
                        "\nRuang yang anda pilih tidak tersedia, silahkan untuk mencoba kembali!");
            } else {
                isListBooking = true;
            }
        }
        // Set status ruangan yang telah dibooking menjadi true
        ruanganTerbooking[ruang - 1] = true;

        String konfirmasiRuangan;
        Boolean isKonfirmasiRuangan = false;
        while (!isKonfirmasiRuangan) {

            System.out.println("\n==================================================");
            System.out.println("=                   Konfirmasi                   =");
            System.out.println("==================================================");

            System.out.print("\nJika anda telah yakin dengan ruangan " + ruang + " ketik (y/t): ");
            konfirmasiRuangan = sc.next();

            if (konfirmasiRuangan.equalsIgnoreCase("y")) {
                System.out.println("\nAnda telah berhasil memesan ruangan " + ruang);
                isKonfirmasiRuangan = true;

            } else if (konfirmasiRuangan.equalsIgnoreCase("t")) {
                System.out.println("\nAnda membatalkan pemesanan ruangan " + ruang);
                // Set status ruangan yang telah dibooking kembali menjadi false
                ruanganTerbooking[ruang - 1] = false;
                isKonfirmasiRuangan = true;

            } else {
                System.out.println("\nInput yang anda masukan salah silahkan coba lagi!");
            }
        }
    }
}
