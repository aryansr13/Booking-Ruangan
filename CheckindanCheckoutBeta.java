import java.util.Scanner;

public class CheckindanCheckoutBeta {

    static Scanner sc = new Scanner(System.in);

    // Boolean menu
    static boolean menu = true;

    // Jam checkin
    static int jamCheckinGlobal;

    // Array bulan
    static String[] arrayBulan = { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus",
            "September",
            "Oktober", "November", "Desember" };

    /*
     * Function Tampilkan Menu
     */
    static void tampilkanMenu() {

        // Boolean do while
        boolean isUserKeluar = false;

        do {// Menu
            System.out.println("==================================================");
            System.out.println("=                      Menu                      =");
            System.out.println("==================================================\n");
            System.out.println("[1] Check-in");
            System.out.println("[2] Check-out");
            System.out.println("[0] Keluar");
            System.out.print("Pilih menu: ");
            int pilihanMenu = sc.nextInt();
            sc.nextLine();

            switch (pilihanMenu) {
                case 1:
                    checkin();
                    break;
                case 2:
                    checkout();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInput yang anda masukan salah, silahkan untuk mencoba lagi!\n");
            }
        } while (!isUserKeluar);
    }

    /*
     * Function Checkin
     */
    static void checkin() {

        System.out.println("\n==================================================");
        System.out.println("=                    Checkin                     =");
        System.out.println("==================================================");

        // Bulan, tanggal, jam checkin
        int bulanCheckin, tanggalCheckin, jamCheckin;
        // Nama bulan, am-pm checkin
        String namaBulanCheckin = "", ampmCheckin = "";

        // input tahun checkin
        System.out.println();
        int tahunCheckin = 0;

        while (tahunCheckin != 2023) {

            System.out.print("Masukan Tahun Checkin (YYYY): ");
            tahunCheckin = sc.nextInt();

            if (tahunCheckin != 2023) {
                System.out.println("\nTahun Checkin yang Anda Masukan Tidak Valid!\n");
            }
        }

        // Bulan checkin
        boolean isBulanValid = false;

        do {
            // input bulan
            System.out.print("Masukkan bulan checkin Anda: ");
            bulanCheckin = sc.nextInt();

            // Menyimpan bulan berdasarkan nomor bulan
            if (bulanCheckin >= 1 && bulanCheckin <= 12) {
                namaBulanCheckin = arrayBulan[bulanCheckin - 1];
                isBulanValid = true;

            } else {
                System.out.println("\nBulan tidak valid!\n");
            }
        } while (!isBulanValid);

        // maksimal tanggal
        int maxTanggalCheckin = 31;

        // Ketika bulan februari
        if (bulanCheckin == 2) {
            // Tahun kabisat
            if ((tahunCheckin % 4 == 0 && tahunCheckin % 100 != 0) || (tahunCheckin % 400 == 0)) {
                maxTanggalCheckin = 29;
                // Bukan tahun kabisat
            } else {
                maxTanggalCheckin = 28;
            }
            // Bulan 4, 6, 9, 11
        } else if (bulanCheckin == 4 || bulanCheckin == 6 || bulanCheckin == 9 || bulanCheckin == 11) {
            maxTanggalCheckin = 30;
        }

        do {
            // input tanggal checkin
            System.out.print("Masukan Tanggal Checkin (1-" + maxTanggalCheckin + "): ");
            tanggalCheckin = sc.nextInt();

            // ketika input salah
            if (tanggalCheckin < 1 || tanggalCheckin > maxTanggalCheckin) {
                System.out.println("Tanggal yang Anda Masukkan Salah untuk Bulan " + namaBulanCheckin + "!");
            }
        } while (tanggalCheckin < 1 || tanggalCheckin > maxTanggalCheckin);

        do {
            // Jam checkin
            System.out.print("Masukan Jam Checkin (format 24 jam): ");
            jamCheckin = sc.nextInt();

            // Ketika input jam salah
            if (jamCheckin < 0 || jamCheckin >= 24) {
                System.out.println("Jam yang Anda Masukkan Salah!");
            }
        } while (jamCheckin < 0 || jamCheckin >= 24);

        // Am-Pm checkin
        ampmCheckin = jamCheckin < 12 ? "AM" : "PM";
        if (jamCheckin > 12) {
            jamCheckin -= 12;
        }

        // waktu checkout
        int waktuCheckout = (jamCheckin + 12) % 24;

        // Am-Pm checkout
        String ampmCheckout = waktuCheckout < 12 ? "AM" : "PM";
        if (waktuCheckout > 12) {
            waktuCheckout -= 12;
        }

        // Input konfirmasi checkin
        String konfirmCheckin;
        boolean isConfirm = false;

        while (!isConfirm) {
            System.out.print("\nApakah anda yakin ingin memulai checkin?(y/t): ");
            konfirmCheckin = sc.next();

            // jika y
            if (konfirmCheckin.equalsIgnoreCase("y")) {

                // Menyimpan jam checkin ke variabel global
                jamCheckinGlobal = jamCheckin;

                // Checkin berhasil
                System.out.println("\nSelamat checkin anda berhasil\n");

                System.out.println(
                        "Anda Memulai Checkin Pada " + tanggalCheckin + " " + namaBulanCheckin + " " + tahunCheckin +
                                " Pada Jam " + jamCheckin + " " + ampmCheckin);
                System.out.println("Anda harus Check-out sebelum jam " + waktuCheckout + " " +
                        ampmCheckout);
                isConfirm = true;

                // Checkin dibatalkan
            } else if (konfirmCheckin.equalsIgnoreCase("t")) {
                System.out.println("Anda batal checkin");
                isConfirm = true;

            } else {
                System.out.println("Input yang anda masukan salah, silahkan coba kembali!");
            }
        }
    }

    /*
     * Function Checkout
     */
    static void checkout() {
        System.out.println("\n==================================================");
        System.out.println("=                   Checkout                     =");
        System.out.println("==================================================\n");

        // Konfirmasi checkout
        boolean konfirmasiCheckout = false;
        while (!konfirmasiCheckout) {
            System.out.print("\nSilahkan Ketik (y) Untuk Checkout dan (t) untuk batal: ");
            String konfirmasi = sc.next();

            // Input y
            if (konfirmasi.equalsIgnoreCase("y")) {
                konfirmasiCheckout = true;
                System.out.println();

                // Input T
            } else if (konfirmasi.equalsIgnoreCase("t")) {
                konfirmasiCheckout = true;
            } else {
                System.out.println("Konfirmasi tidak sesuai! Silahkan coba lagi!\n");
            }
        }

        // Input waktu checkout
        int jamCheckout;

        do {
            System.out.print("Masukan Jam Checkout (format 24 jam): ");
            jamCheckout = sc.nextInt();

            if (jamCheckout < 0 || jamCheckout >= 24) {
                System.out.println("Jam checkout yang Anda Masukan Salah!\n");
            }

        } while (jamCheckout < 0 || jamCheckout >= 24);

        // Am-Pm Checkout
        String ampmCheckout = jamCheckout < 12 ? "AM" : "PM";

        // Mengambil jam checkin dari variabel global
        int jamCheckin = jamCheckinGlobal;

        // Menambah 24 jam jika waktu checkout lebih kecil dari waktu checkin
        if (jamCheckout < jamCheckin) {
            jamCheckout += 24;
        }

        // Menghitung durasi menginap
        int durasiInapJam = jamCheckout - jamCheckin;

        if (durasiInapJam <= 12) {
            // User menginaop <= 12 jam
            System.out.println("Anda telah menginap selama " + durasiInapJam + " jam. Tidak ada denda.\n");

        } else {
            // User menginap lebih dari 12 Jam
            int denda = (durasiInapJam - 12) * 50000; // Denda 50.000 per jam melebihi 12 jam
            System.out.println("Anda telah menginap selama " + durasiInapJam +
                    " jam. Anda dikenakan denda sebesar Rp " + denda + ".\n");
        }

    }

    public static void main(String[] args) {
        do {
            tampilkanMenu();
        } while (menu);
    }
}